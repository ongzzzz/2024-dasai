package com.example.xiyouji.result_rank_comment.controller.api;

import com.example.xiyouji.result_rank_comment.dto.*;
import com.example.xiyouji.result_rank_comment.service.CommentService;
import com.example.xiyouji.result_rank_comment.dto.CommentResponse;
import com.example.xiyouji.result_rank_comment.service.RankingQuizService;
import com.example.xiyouji.translate.service.impl.BaiduTranslator;
import com.example.xiyouji.type.Language;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RankingQuizCommentRestController {

    private final RankingQuizService rankingQuizService;
    private final CommentService commentService;
    private final BaiduTranslator translator;


    @PostMapping("/quiz/result/{userId}")
    public ResponseEntity<RankingQuizCommentResponse>
    saveQuizResult(@PathVariable("userId") Long userId, @RequestBody List<String> characters){
        UserRankingResponse userRankingResponse =
                rankingQuizService.saveUserRankingAndIsUserInTopFive(userId, characters);
        List<RankingDto> rankingTopTen = rankingQuizService.getRankingTopFive();
        Page<CommentResponse> commentsResponse =
                commentService.getComments(PageRequest.of(0, 5, Sort.by("createdDate").descending()));

        return ResponseEntity.ok(
                RankingQuizCommentResponse.of(rankingTopTen, userRankingResponse, commentsResponse)
                );
    }

    @PostMapping("/comment/{userId}")
    public ResponseEntity<Page<CommentResponse>> saveComment(
            @PathVariable(name = "userId")
            Long userId,
            @RequestBody CommentRequest commentRequest
    ){
        commentService.saveComment(userId, commentRequest.comment());
        Page<CommentResponse> comments =
                commentService.getComments(
                        PageRequest.of(0, 5, Sort.by("createdDate").descending())
                );
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/comment")
    public ResponseEntity<Page<CommentResponse>> getComments(
            @PageableDefault(sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable)
    {
        return ResponseEntity.ok(commentService.getComments(pageable));
    }

    @GetMapping("/comment/translate/{language}")
    public ResponseEntity<TranslateResponse> getCommentTranslate(
            @PathVariable(name = "language") String language,
            @RequestBody CommentRequest commentRequest
    ) throws JsonProcessingException {
        Language languageType = Language.fromString(language);

        String translationResult = languageType.getValue_baidu().equals("kor") ?
                translator.translate(commentRequest.comment(), Language.CN, Language.KR) :
                translator.translate(commentRequest.comment(), Language.KR, Language.CN);

        return ResponseEntity.ok(TranslateResponse.of(translationResult));
    }
}

