package com.example.xiyouji.result_rank_comment.dto;

import com.example.xiyouji.result_rank_comment.service.CommentsResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public record RankingQuizCommentResponse(

        List<RankingDto> rankingTopTen,
        UserRankingResponse userRanking,
        Page<CommentsResponse> comments

) {

    public static RankingQuizCommentResponse of(
            List<RankingDto> rankingTopTen,
            UserRankingResponse userRanking,
            Page<CommentsResponse> comments
    ){
        return new RankingQuizCommentResponse(rankingTopTen, userRanking, comments);
    }
}
