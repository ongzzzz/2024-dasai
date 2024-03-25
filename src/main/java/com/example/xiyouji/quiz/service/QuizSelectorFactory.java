package com.example.xiyouji.quiz.service;

import com.example.xiyouji.exception.RestApiException;
import com.example.xiyouji.exception.impl.CommonErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Getter
public class QuizSelectorFactory {

    private final List<QuizSelector> quizSelectors;

    public QuizSelector find(String type) {
        return quizSelectors.stream()
                .filter(quizSelector -> quizSelector.getType().equals(type))
                .findFirst()
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
    }
}
