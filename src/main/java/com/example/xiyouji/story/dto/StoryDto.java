package com.example.xiyouji.story.dto;

import com.example.xiyouji.type.Characters;
import com.example.xiyouji.type.Language;
import lombok.Builder;
import lombok.Data;

import java.util.List;

public class StoryDto {

    @Data
    public static class StoryRequestDto {

        private Long storyId;

        private String storyTitle;

        private Language language;

        private Characters character;

        @Builder
        public StoryRequestDto(Long storyId, String storyTitle, Language language, Characters character) {
            this.storyId = storyId;
            this.storyTitle = storyTitle;
            this.language = language;
            this.character = character;
        }
    }

    @Data
    public static class StoryResponseDto {

        private List<String> storyContents;

        private List<String> storyImagesUrl;

        @Builder
        public StoryResponseDto(List<String> storyContents, List<String> storyImagesUrl) {
            this.storyContents = storyContents;
            this.storyImagesUrl = storyImagesUrl;
        }
    }

}
