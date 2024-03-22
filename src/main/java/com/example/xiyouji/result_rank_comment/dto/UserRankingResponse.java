package com.example.xiyouji.result_rank_comment.dto;

import com.example.xiyouji.type.Characters;

import java.util.List;

public record UserRankingResponse(
        Long userId,

        Long rankingCount,
        Integer quizResult,
        String nickName,
        List<Characters> characters
) {

    public static UserRankingResponse of(
            Long userId,
            Long rankingCount,
            Integer quizResult,
            String nickName,
            List<Characters> characters
    ){
        return new UserRankingResponse(userId, rankingCount, quizResult, nickName, characters);
    }

    public static UserRankingResponse empty() {
        return null;
    }
}
