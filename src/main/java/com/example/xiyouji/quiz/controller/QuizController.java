package com.example.xiyouji.quiz.controller;

import com.example.xiyouji.login.service.UserService;
import com.example.xiyouji.quiz.dto.QuizDto;
import com.example.xiyouji.quiz.respository.QuizRepository;
import com.example.xiyouji.quiz.service.QuizService;
import com.example.xiyouji.quiz.vo.ChoiceQuiz;
import com.example.xiyouji.quiz.vo.Quiz;
import com.example.xiyouji.type.Characters;
import com.example.xiyouji.type.Language;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.IntStream;

@RestController
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    private final UserService userService;

    //private final QuizRepository quizRepository;

    @GetMapping("/quiz/start/{nickName}/{language}")
    public ResponseEntity<List<QuizDto.QuizResponseDto>> getQuizzes(
            @PathVariable String language,
            @PathVariable String nickName) {

        QuizDto.QuizRequestDto quizRequestDto = QuizDto.QuizRequestDto.builder()
                .language(Language.fromString(language))
                .selectorType("sameNum")
                .build();

        Long userId = userService.signup(nickName);

        return ResponseEntity.ok(quizService.getQuizzesWithUserId(quizRequestDto, userId));
    }

    /*@GetMapping("/quiz/make")
    public void makeQuizzes() {

        List<? extends Quiz> quizzes = IntStream.range(0, 12)
                .mapToObj(i -> ChoiceQuiz.builder()
                        .quizContent("quiz" + i)
                        .options(List.of("보기"))
                        .answerNum(3)
                        .characterType(Characters.사오정)
                        .answerDescription("정답 설명")
                        .language(Language.KR)
                        .build())
                .toList();

        quizRepository.saveAll(quizzes);
    }*/

}
