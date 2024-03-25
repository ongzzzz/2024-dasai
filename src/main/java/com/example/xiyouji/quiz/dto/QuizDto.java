package com.example.xiyouji.quiz.dto;

import com.example.xiyouji.type.Language;
import lombok.Builder;
import lombok.Data;

import java.util.List;

public class QuizDto {

    @Data
    public static class QuizRequestDto {
        private Language language;

        private String selectorType;

        @Builder
        public QuizRequestDto(Language language, String selectorType) {
            this.language = language;
            this.selectorType = selectorType;
        }
    }

    @Data
    public static class QuizResponseDto {
        private String quizContent;

        private String characterType;

        private Object answer;

        private List<String> options;

        private String answerDescription;

        @Builder
        public QuizResponseDto(String quizContent, String characterType, Object answer, List<String> options, String answerDescription) {
            this.quizContent = quizContent;
            this.characterType = characterType;
            this.answer = answer;
            this.options = options;
            this.answerDescription = answerDescription;
        }
    }
}
