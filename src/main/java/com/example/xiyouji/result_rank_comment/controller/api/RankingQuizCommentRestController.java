package com.example.xiyouji.result_rank_comment.controller.api;

import com.example.xiyouji.result_rank_comment.dto.*;
import com.example.xiyouji.result_rank_comment.service.CommentService;
import com.example.xiyouji.result_rank_comment.dto.CommentsResponse;
import com.example.xiyouji.result_rank_comment.service.RankingQuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RankingQuizCommentRestController {

    private final RankingQuizService rankingQuizService;
    private final CommentService commentService;


    @PostMapping("/quiz/result/{userId}")
    public ResponseEntity<RankingQuizCommentResponse>
    saveQuizResult(@PathVariable Long userId, @RequestBody List<String> characters){
        UserRankingResponse userRankingResponse =
                rankingQuizService.saveUserRankingAndIsUserInTopFive(userId, characters);
        List<RankingDto> rankingTopTen = rankingQuizService.getRankingTopFive();
        Page<CommentsResponse> commentsResponse =
                commentService.getComments(PageRequest.of(0, 5, Sort.by("createdDate").descending()));

        return ResponseEntity.ok(
                RankingQuizCommentResponse.of(rankingTopTen, userRankingResponse, commentsResponse)
                );
    }
}

