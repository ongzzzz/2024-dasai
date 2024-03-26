package com.example.xiyouji.story.vo;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class StoryImage {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Story story;

    private String filename;

    @Builder
    public StoryImage(Story story, String filename) {
        this.story = story;
        this.filename = filename;
    }
}
