package com.example.xiyouji.result_rank_comment.controller.api;

import com.example.xiyouji.result_rank_comment.dto.*;
import com.example.xiyouji.result_rank_comment.service.CommentService;
import com.example.xiyouji.result_rank_comment.dto.CommentResponse;
import com.example.xiyouji.result_rank_comment.service.RankingQuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class RankingQuizCommentRestController {

    private final RankingQuizService rankingQuizService;
    private final CommentService commentService;


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
            @RequestBody String comment
    ){
        commentService.saveComment(userId, comment);
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
}

