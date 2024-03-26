package com.example.xiyouji.story.vo;

import com.example.xiyouji.store.UploadFile;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class StoryContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Story story;

    @Column(nullable = false)
    private String content;

    @Builder
    public StoryContent(Story story, String content) {
        this.story = story;
        this.content = content;
    }
}
