package com.example.xiyouji.quiz.controller;

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

    @GetMapping("/quiz/start/{nickName}/{language}")
    public ResponseEntity<List<QuizDto.QuizResponseDto>> getQuizzes(@PathVariable String language) {
        QuizDto.QuizRequestDto quizRequestDto = QuizDto.QuizRequestDto.builder()
                .language(Language.fromString(language))
                .selectorType("random")
                .build();

        return ResponseEntity.ok(quizService.getQuizzes(quizRequestDto));
    }
}
