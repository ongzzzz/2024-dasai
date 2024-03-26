package com.example.xiyouji.quiz.service.impl;

import com.example.xiyouji.instance.Instance;
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
    public String getType() {
        return "sameNum";
    }

    @Override
    public List<Quiz> selectQuiz(List<Quiz> quizzes) {
        List<Characters> characters = List.of(Characters.values());


        return shuffle(characters.stream()
                .flatMap(type -> shuffle(getQuizzesByCharacterType(quizzes, type)).stream()
                        .limit(Instance.CHARACTER_QUIZ_NUM))
                .toList());

    }

    private List<Quiz> getQuizzesByCharacterType(List<Quiz> quizzes, Characters charactersType) {
        return quizzes.stream()
                .filter(quiz -> quiz.getCharacterType().get(0).equals(charactersType))
                .toList();
    }

    private List<Quiz> shuffle(List<Quiz> quizzes) {
        List<Quiz> arrayList = new ArrayList<>(quizzes);
        Collections.shuffle(arrayList);

        return arrayList;
    }
}
