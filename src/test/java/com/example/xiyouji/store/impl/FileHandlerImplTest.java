package com.example.xiyouji.store.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileHandlerImplTest {


    @Autowired
    private FileHandlerImpl fileHandler;


    @Test
    void getFileFullUrl() {
        String filename = "파일.jpg";

        String fullUrl = fileHandler.getFullPath(filename);

        System.out.println("파일url" + fullUrl);
    }
}