package com.example.xiyouji.store.impl;

import com.example.xiyouji.store.FileHandler;
import com.example.xiyouji.store.InMemoryMultipartFile;
import com.example.xiyouji.store.UploadFile;
import com.example.xiyouji.store.UploadFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.*;

@Component
@RequiredArgsConstructor
public class FileHandlerImpl implements FileHandler {

    private final UploadFileRepository uploadFileRepository;


    String rootPath = System.getProperty("user.dir");

    // 프로젝트 루트 경로에 있는 files 디렉토리
    private final String fileDir = rootPath + "/files/";

    @Override
    public String getFullPath(String filename) { return fileDir + filename; }

    @Override
    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()) {
            return null;
        }



        String originalFilename = multipartFile.getOriginalFilename();
        // 작성자가 업로드한 파일명 -> 서버 내부에서 관리하는 파일명
        // 파일명을 중복되지 않게끔 UUID로 정하고 ".확장자"는 그대로

        String storeFilename = UUID.randomUUID() + "." + extractExt(originalFilename);
        //System.out.println(getFullPath(storeFilename));
        // 파일을 저장하는 부분 -> 파일경로 + storeFilename 에 저장

        multipartFile.transferTo(Path.of(getFullPath(storeFilename)));

        return uploadFileRepository.save(UploadFile.builder()
                .orgFileName(originalFilename)
                .storeFileName(storeFilename)
                .build());
    }

    // 파일이 여러개 들어왔을 때 처리해주는 부분
    @Override
    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles, Object objectBy) throws IOException {
        List<UploadFile> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if(!multipartFile.isEmpty()) {
                storeFileResult.add(storeFile(multipartFile));
            }
        }
        return storeFileResult;
    }

    @Override
    public List<InMemoryMultipartFile> extractFiles(List<? extends UploadFile> uploadFiles)  {
        List<InMemoryMultipartFile> list = new ArrayList<>();
        for (UploadFile file : uploadFiles) {
            list.add(extractFile(file));
        }
        return list;
    }

    @Override
    public boolean deleteFile(UploadFile uploadFile) {
        String storeFileName = uploadFile.getStoreFileName();
        String filePath = getFullPath(storeFileName);
        File file = new File(filePath);

        if (file.exists()) {
            if (file.delete()) {
                return true; // File deleted successfully
            } else {
                return false; // Failed to delete the file
            }
        } else {
            return true; // File doesn't exist, treat as a successful deletion
        }
    }

    @Override
    public boolean deleteFiles(List<? extends UploadFile> uploadFiles) {
        for (UploadFile file : uploadFiles) {
            if (!deleteFile(file)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public InMemoryMultipartFile extractFile(UploadFile uploadFile) {
        if (uploadFile == null) {
            return null;
        }
        File inMemoryFile = new File(getFullPath(uploadFile.getStoreFileName()));
        byte[] fileContent = new byte[0];
        try {
            fileContent = FileCopyUtils.copyToByteArray(inMemoryFile);
        } catch (IOException e) {
            return null;
            //todo 처리 방식 고민 오류를 터뜨릴지 null 을 반환 할지.
        }
        return new InMemoryMultipartFile(uploadFile.getOrgFileName(), fileContent);
    }

    // 확장자 추출
    @Override
    public String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }

    public static String multipartFileToString(InMemoryMultipartFile inMemoryMultipartFile) {
        return Optional.ofNullable(inMemoryMultipartFile)
                .map(file -> {
                    try {
                        return Base64.getEncoder().encodeToString(file.getBytes());
                    } catch (IOException e) {
                        throw new UncheckedIOException("이미지 변환 실패", e);
                    }
                })
                .orElse("");
    }
}
