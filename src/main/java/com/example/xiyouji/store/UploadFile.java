package com.example.xiyouji.store;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
public class UploadFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orgFileName;

    private String storeFileName;


    @Builder
    public UploadFile(Long id, String orgFileName, String storeFileName) {
        this.id = id;
        this.orgFileName = orgFileName;
        this.storeFileName = storeFileName;
    }
}
