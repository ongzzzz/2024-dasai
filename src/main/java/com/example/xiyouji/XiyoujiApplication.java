package com.example.xiyouji;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class XiyoujiApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiyoujiApplication.class, args);
    }

}
