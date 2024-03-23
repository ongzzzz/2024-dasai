package com.example.xiyouji.story.vo;

import com.example.xiyouji.store.UploadFile;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Getter
@RequiredArgsConstructor
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Story story;

    private String storyStr;

    @OneToOne
    private UploadFile storyImg;

    @Builder
    public Content(Long id, Story story, String storyStr, UploadFile storyImg) {
        this.id = id;
        this.story = story;
        this.storyStr = storyStr;
        this.storyImg = storyImg;
    }
}
