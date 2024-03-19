package com.example.xiyouji.story.dto;

import com.example.xiyouji.type.Characters;
import com.example.xiyouji.type.Language;
import lombok.Builder;
import lombok.Data;

public class StoryDto {

    @Data
    public static class StoryRequestDto {
        private String storyTitle;

        private Language language;

        private Characters character;

        @Builder
        public StoryRequestDto(String storyTitle, Language language, Characters character) {
            this.storyTitle = storyTitle;
            this.language = language;
            this.character = character;
        }
    }

    @Data
    public static class StoryResponseDto {

        private String storyContent;

        @Builder
        public StoryResponseDto(String storyContent) {
            this.storyContent = storyContent;
        }
    }

}
