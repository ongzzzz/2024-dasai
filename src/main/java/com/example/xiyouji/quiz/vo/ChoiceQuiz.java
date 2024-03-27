package com.example.xiyouji.quiz.vo;

import com.example.xiyouji.quiz.dto.QuizDto;
import com.example.xiyouji.type.Characters;
import com.example.xiyouji.type.Language;
import jakarta.persistence.ElementCollection;
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
    private Integer answerNum;

    @ElementCollection
    private List<String> options; // 문제 보기를 저장하는 필드

    @Builder
    public ChoiceQuiz(Long id, Characters characterType, String quizContent, String answerDescription, Language language, Integer answerNum, List<String> options) {
        super(id, characterType, quizContent, answerDescription, language);
        this.answerNum = answerNum;
        this.options = options;
    }

/*    private void validate(List<Characters> charactersType, String quizContent, Language language, Integer answer) {

    }*/

    @Override
    public QuizDto.QuizResponseDto toQuizResponse() {
        return QuizDto.QuizResponseDto.builder()
                .characterType(getCharacterType().getValue_kr())
                .answer(answerNum)
                .options(options)
                .answerDescription(getAnswerDescription())
                .quizContent(getQuizContent())
                .build();
    }

}
