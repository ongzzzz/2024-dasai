package com.example.xiyouji.store;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@RequiredArgsConstructor
public class UploadFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String storeFileName;


    @Builder
    public UploadFile(Long id, String storeFileName) {
        this.id = id;
        this.storeFileName = storeFileName;
    }
}
