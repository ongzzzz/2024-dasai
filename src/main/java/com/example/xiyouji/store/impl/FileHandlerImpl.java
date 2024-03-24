package com.example.xiyouji.store.impl;

import com.example.xiyouji.store.FileHandler;
import com.example.xiyouji.store.InMemoryMultipartFile;
import com.example.xiyouji.store.UploadFile;
import com.example.xiyouji.store.repository.UploadFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FileHandlerImpl implements FileHandler {

    private final UploadFileRepository uploadFileRepository;


    String rootPath = System.getProperty("user.dir");

    // 프로젝트 루트 경로에 있는 files 디렉토리
    private final String fileDir = rootPath + "/front/images";

    @Override
    public String getFullPath(String filename) { return fileDir + filename; }

    @Override
    public List<String> getFullPaths(List<String> filenames) {
        return filenames.stream()
                .map(filename -> fileDir + filename)
                .toList();
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
        return new InMemoryMultipartFile(uploadFile.getStoreFileName(), fileContent);
    }

    // 확장자 추출
    @Override
    public String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }


}
