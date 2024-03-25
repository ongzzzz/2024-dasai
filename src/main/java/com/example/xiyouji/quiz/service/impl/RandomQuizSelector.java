package com.example.xiyouji.quiz.service.impl;

import com.example.xiyouji.quiz.service.QuizSelector;
import com.example.xiyouji.quiz.vo.Quiz;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RandomQuizSelector implements QuizSelector {

    @Override
    public List<Quiz> selectQuiz(List<Quiz> quizzes, Integer num) {
        Collections.shuffle(quizzes);

        return quizzes.stream()
                .limit(num)
                .collect(Collectors.toList());

    }

    @Override
    public String getType() {
        return "random";
    }
}
