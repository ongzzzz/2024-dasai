package com.example.xiyouji.quiz.vo;

import com.example.xiyouji.quiz.dto.QuizDto;
import com.example.xiyouji.type.Characters;
import com.example.xiyouji.type.Language;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ChoiceQuiz extends Quiz {
    private Integer answer;



    @Builder
    public ChoiceQuiz(Long id, Characters characterType, String quizContent, Language language, Integer answer) {
        super(id, characterType, quizContent, language);
        this.answer = answer;
    }

    @Override
    public QuizDto.QuizResponseDto toQuizResponse() {
        return QuizDto.QuizResponseDto.builder()
                .quizContent(getQuizContent())
                .result(answer)
                .characterType(getCharacterType().getValue_kr())
                .build();
    }

}
