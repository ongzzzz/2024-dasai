package com.example.xiyouji.store;


import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FileConvert {

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

    public static List<String> multipartFilesToStrings(List<InMemoryMultipartFile> inMemoryMultipartFiles) {
        return inMemoryMultipartFiles.stream()
                .map(file -> {
                    try {
                        return Base64.getEncoder().encodeToString(file.getBytes());
                    } catch (IOException e) {
                        throw new UncheckedIOException("이미지 변환 실패", e);
                    }
                })
                .collect(Collectors.toList());
    }
}
