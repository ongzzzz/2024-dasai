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
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RandomQuizSelectorTest {

    @Autowired
    private RandomQuizSelector selector;
    @Test
    public void testSelectQuiz_ReturnsCorrectNumberOfQuizzes() {
        List<? extends Quiz> quizz_choice = IntStream.range(0, 2).mapToObj(
                i ->  ChoiceQuiz.builder()
                        .language(Language.CN)
                        .quizContent("内容 " + i)
                        .characterType(List.of(Characters.사오정))
                        .build()
        ).toList();

        List<Quiz> quizzes = new ArrayList<>(quizz_choice.subList(0, 2));
        List<Quiz> selectedQuizzes = selector.selectQuiz(quizzes);

        assertEquals(2, selectedQuizzes.size(), "Should return exactly 2 quizzes.");
    }

    @Test
    public void testSelectQuiz_ReturnsAllQuizzesIfRequestedMoreThanAvailable() {
        List<? extends Quiz> quizz_choice = IntStream.range(0, 2).mapToObj(
                i ->  ChoiceQuiz.builder()
                        .language(Language.CN)
                        .quizContent("内容 " + i)
                        .characterType(List.of(Characters.사오정))
                        .build()
        ).toList();

        List<Quiz> quizzes = new ArrayList<>(quizz_choice.subList(0, 2));

        List<Quiz> selectedQuizzes = selector.selectQuiz(quizzes);

        assertEquals(quizzes.size(), selectedQuizzes.size(), "Should return all available quizzes.");
    }

    @Test
    public void testSelectQuiz_ResultsAreRandomlyShuffled() {
        List<? extends Quiz> quizz_choice = IntStream.range(0, 17).mapToObj(
                i ->  ChoiceQuiz.builder()
                        .language(Language.CN)
                        .quizContent("内容 " + i)
                        .characterType(List.of(Characters.사오정))
                        .build()
        ).toList();

        List<Quiz> quizzes = new ArrayList<>(quizz_choice.subList(0, 17));

        boolean isDifferent = false;
        List<Quiz> firstSelection = selector.selectQuiz(new ArrayList<>(quizzes));
        for (int i = 0; i < Instance.QUIZ_NUM; i++) {
            List<Quiz> newSelection = selector.selectQuiz(new ArrayList<>(quizzes));
            if (!firstSelection.equals(newSelection)) {
                isDifferent = true;
                break;
            }
        }

        assertTrue(isDifferent, "The selected quizzes should be shuffled and different between executions.");
    }
}