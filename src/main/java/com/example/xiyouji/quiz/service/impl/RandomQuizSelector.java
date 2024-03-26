package com.example.xiyouji.quiz.service.impl;

import com.example.xiyouji.instance.Instance;
import com.example.xiyouji.quiz.service.QuizSelector;
import com.example.xiyouji.quiz.vo.Quiz;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RandomQuizSelector implements QuizSelector {

    @Override
    public String getType() {
        return "random";
    }

    @Override
    public List<Quiz> selectQuiz(List<Quiz> quizzes) {
        return shuffle(quizzes.stream()
                .limit(Instance.QUIZ_NUM)
                .collect(Collectors.toList()));

    }

    private List<Quiz> shuffle(List<Quiz> quizzes) {
        List<Quiz> arrayList = new ArrayList<>(quizzes);
        Collections.shuffle(arrayList);

        return arrayList;
    }


}
