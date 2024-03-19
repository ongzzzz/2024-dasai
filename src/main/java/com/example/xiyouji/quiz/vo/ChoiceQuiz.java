package com.example.xiyouji.quiz.vo;

import com.example.xiyouji.quiz.dto.QuizDto;
import com.example.xiyouji.type.Characters;
import com.example.xiyouji.type.Language;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ChoiceQuiz extends Quiz {
    private Integer answer;

    @Builder
    public ChoiceQuiz(Long id, List<Characters> characterType, String quizContent, Language language, Integer answer) {
        super(id, characterType, quizContent, language);
        this.answer = answer;
    }

    @Override
    public QuizDto.QuizResponseDto toQuizResponse() {
        return QuizDto.QuizResponseDto.builder()
                .characterType(getCharacterType().stream()
                        .map(Characters::getValue_kr)
                        .toList())
                .result(answer)
                .quizContent(getQuizContent())
                .build();
    }

}
