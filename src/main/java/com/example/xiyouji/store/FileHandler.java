package com.example.xiyouji.store;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileHandler{
    String getFullPath(String filename);

    List<String> getFullPaths(List<String> filenames);

    List<InMemoryMultipartFile> extractFiles(List<? extends UploadFile> uploadFiles);

    InMemoryMultipartFile extractFile(UploadFile uploadFile);

    String extractExt(String originalFilename);


}
