package com.example.xiyouji.quiz.service;

import com.example.xiyouji.quiz.dto.QuizDto;
import com.example.xiyouji.quiz.respository.QuizRepository;
import com.example.xiyouji.quiz.service.impl.RandomQuizSelector;
import com.example.xiyouji.quiz.vo.Quiz;
import com.example.xiyouji.type.Language;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.xiyouji.instance.Instance.CHARACTER_QUIZ_NUM;
import static com.example.xiyouji.instance.Instance.QUIZ_NUM;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizSelectorFactory quizSelectorFactory;

    private final QuizRepository quizRepository;

    public List<QuizDto.QuizResponseDto> getQuizzes(QuizDto.QuizRequestDto quizRequestDto) {
        List<Quiz> quizzes = quizRepository.findQuizzesByLanguage(quizRequestDto.getLanguage());
        QuizSelector quizSelector = quizSelectorFactory.find(quizRequestDto.getSelectorType());

        List<Quiz> randomQuizzes = quizSelector.selectQuiz(quizzes);

        return randomQuizzes.stream()
                .map(Quiz::toQuizResponse)
                .toList();
    }
}
