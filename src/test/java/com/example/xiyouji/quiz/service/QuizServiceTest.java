package com.example.xiyouji.quiz.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.xiyouji.quiz.dto.QuizDto;
import com.example.xiyouji.quiz.respository.QuizRepository;
import com.example.xiyouji.quiz.service.impl.RandomQuizSelector;
import com.example.xiyouji.quiz.vo.ChoiceQuiz;
import com.example.xiyouji.quiz.vo.Quiz;
import com.example.xiyouji.story.repository.StoryRepository;
import com.example.xiyouji.story.service.StoryService;
import com.example.xiyouji.type.Characters;
import com.example.xiyouji.type.Language;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest

public class QuizServiceTest {

    @Autowired
    private QuizService quizService;

    @MockBean
    private QuizRepository quizRepository;

    @Test
    public void whenGetQuizRequestByQuizNum_outputOnlyQuizNum() {
        // 테스트 데이터 준비
        QuizDto.QuizRequestDto quizRequestDto = QuizDto.QuizRequestDto.builder()
                .language(Language.KR)
                .build();

        List<? extends Quiz> makeQuiz = IntStream.range(0, 20).mapToObj(
                i -> ChoiceQuiz.builder()
                        .language(Language.KR)
                        .quizContent("내용 " + i)
                        .characterType(List.of(Characters.사오정))
                        .build()
        ).toList();

        List<Quiz> quizzes = new ArrayList<>(makeQuiz.subList(0, 10));

        when(quizRepository.findQuizzesByLanguage(Language.KR)).thenReturn(quizzes);

        // 테스트 실행
        List<QuizDto.QuizResponseDto> result = quizService.getQuizzes(quizRequestDto);

        // 검증
        assertEquals(10, result.size()); // 예상되는 결과 크기 검증
        verify(quizRepository).findQuizzesByLanguage(Language.KR);
    }

    @Test
    @Transactional
    public void whenCNQuizRequest_onlyResponseCNQuiz() {
        // 테스트 데이터 준비
        QuizDto.QuizRequestDto quizRequestDto = QuizDto.QuizRequestDto.builder()
                .language(Language.CN)
                .build();

        List<? extends Quiz> quiz_cn = IntStream.range(0, 7).mapToObj(
                i -> ChoiceQuiz.builder()
                        .language(Language.CN)
                        .quizContent("内容 " + i)
                        .characterType(List.of(Characters.사오정))
                        .build()
        ).toList();

        List<Quiz> quizzes = new ArrayList<>(quiz_cn.subList(0, 7));

        when(quizRepository.findQuizzesByLanguage(Language.CN)).thenReturn(quizzes);

        // 테스트 실행
        List<QuizDto.QuizResponseDto> result = quizService.getQuizzes(quizRequestDto);

        // 검증
        assertEquals(7, result.size()); // 예상되는 결과 크기 검증
        verify(quizRepository).findQuizzesByLanguage(Language.CN);
    }
}
