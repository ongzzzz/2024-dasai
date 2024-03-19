package com.example.xiyouji.quiz.service;

import com.example.xiyouji.quiz.dto.QuizDto;
import com.example.xiyouji.quiz.respository.QuizRepository;
import com.example.xiyouji.quiz.vo.Quiz;
import com.example.xiyouji.type.Language;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.xiyouji.instance.Instance.QUIZ_NUM;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizSelector quizSelector;

    private final QuizRepository quizRepository;

    public List<QuizDto.QuizResponseDto> getQuizzes(QuizDto.QuizRequestDto quizRequestDto) {
        List<Quiz> quizzes = quizRepository.findQuizzesByLanguage(quizRequestDto.getLanguage());

        List<Quiz> randomQuizzes = quizSelector.selectQuiz(quizzes, QUIZ_NUM);

        return randomQuizzes.stream()
                .map(Quiz::toQuizResponse)
                .toList();
    }
}
