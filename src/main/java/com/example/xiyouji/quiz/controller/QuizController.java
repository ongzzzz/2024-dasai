package com.example.xiyouji.quiz.controller;

import com.example.xiyouji.exception.RestApiException;
import com.example.xiyouji.exception.impl.StoryErrorCode;
import com.example.xiyouji.login.UserService;
import com.example.xiyouji.quiz.dto.QuizDto;
import com.example.xiyouji.quiz.service.QuizService;
import com.example.xiyouji.type.Language;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    private final UserService userService;

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

}
