package com.example.xiyouji.quiz.service.impl;

import com.example.xiyouji.quiz.service.QuizSelector;
import com.example.xiyouji.quiz.vo.Quiz;
import com.example.xiyouji.type.Characters;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class SameNumQuizSelector implements QuizSelector {

    @Override
    public List<Quiz> selectQuiz(List<Quiz> quizzes, Integer num) {
        List<Characters> characters = List.of(Characters.values());


        return shuffle(characters.stream()
                .flatMap(type -> shuffle(getQuizzesByCharacterType(quizzes, type)).stream()
                        .limit(num))
                .toList());

    }

    @Override
    public String getType() {
        return "sameNum";
    }

    private List<Quiz> getQuizzesByCharacterType(List<Quiz> quizzes, Characters charactersType) {
        return quizzes.stream()
                .filter(quiz -> quiz.getCharacterType().equals(charactersType))
                .toList();
    }

    private List<Quiz> shuffle(List<Quiz> quizzes) {
        Collections.shuffle(quizzes);

        return quizzes;
    }
}
