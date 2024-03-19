package com.example.xiyouji.quiz.vo;

import com.example.xiyouji.type.Characters;
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
    public ChoiceQuiz(Long id, Characters characterType, String content_kr, String content_cn, Integer answer) {
        super(id, characterType, content_kr, content_cn);
        this.answer = answer;
    }
}
