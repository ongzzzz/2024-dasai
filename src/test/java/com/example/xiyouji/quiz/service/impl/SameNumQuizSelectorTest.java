package com.example.xiyouji.quiz.service.impl;

import com.example.xiyouji.instance.Instance;
import com.example.xiyouji.quiz.vo.ChoiceQuiz;
import com.example.xiyouji.quiz.vo.Quiz;
import com.example.xiyouji.type.Characters;
import com.example.xiyouji.type.Language;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SameNumQuizSelectorTest {
    @Autowired
    private SameNumQuizSelector selector;

    @Test
    void testSelectQuizWithEmptyList() {
        assertTrue(selector.selectQuiz(new ArrayList<>()).isEmpty(), "Empty list should return an empty list");
    }

    @Test
    void lessThanCharacterTypeQuizNum_responseAllQuiz() {
        List<Quiz> quizzes = List.of(ChoiceQuiz.builder()
                .characterType(Characters.손오공)
                .quizContent("손오공의 관한 문제")
                .language(Language.KR)
                .answerNum(3)
                .build());

        List<Quiz> result = selector.selectQuiz(quizzes);
        assertEquals(1, result.size());
    }

    @Test
    void getQuiz_eachCharacterQuizzesHaveCharacter_Quiz_Num() {
        List<? extends Quiz> character1 = new ArrayList<>(IntStream.range(0, 10)
                .mapToObj(i -> ChoiceQuiz.builder()
                        .characterType(Characters.손오공)
                        .quizContent("손오공" + i)
                        .answerNum(3)
                        .language(Language.KR)
                        .build())
                .toList());

        List<? extends Quiz> character2 = new ArrayList<>(IntStream.range(0, 10)
                .mapToObj(i -> ChoiceQuiz.builder()
                        .characterType(Characters.사오정)
                        .quizContent("사오정" + i)
                        .answerNum(3)
                        .language(Language.KR)
                        .build())
                .toList());

        List<? extends Quiz> character3 = new ArrayList<>(IntStream.range(0, 10)
                .mapToObj(i -> ChoiceQuiz.builder()
                        .characterType(Characters.저팔계)
                        .quizContent("저팔계" + i)
                        .answerNum(3)
                        .language(Language.KR)
                        .build())
                .toList());


        List<? extends Quiz> character4 = new ArrayList<>(IntStream.range(0, 10)
                .mapToObj(i -> ChoiceQuiz.builder()
                        .characterType(Characters.삼장법사)
                        .quizContent("삼장법사" + i)
                        .answerNum(3)
                        .language(Language.KR)
                        .build())
                .toList());


        List<Quiz> combinedList = new ArrayList<>();
        combinedList.addAll(character1);
        combinedList.addAll(character2);
        combinedList.addAll(character3);
        combinedList.addAll(character4);

        List<Quiz> result = selector.selectQuiz(combinedList);
        assertEquals(Instance.CHARACTER_QUIZ_NUM * 4, result.size());
        assertEquals(Instance.CHARACTER_QUIZ_NUM, result.stream()
                .filter(quiz -> quiz.getCharacterType().equals(Characters.손오공))
                .toList()
                .size());
        assertEquals(Instance.CHARACTER_QUIZ_NUM, result.stream()
                .filter(quiz -> quiz.getCharacterType().equals(Characters.삼장법사))
                .toList()
                .size());
    }


    @Test
    void testGetType() {
        assertEquals("sameNum", selector.getType(), "getType should return 'sameNum'");
    }
}