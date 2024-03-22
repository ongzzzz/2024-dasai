package com.example.xiyouji.result_rank_comment.dto;

import com.example.xiyouji.type.Characters;

import java.util.List;

public record RankingTopTenResponse(

        Long userId,
        Integer quizResult,
        String nickName,
        List<Characters> characters
) {
    public static RankingTopTenResponse of(
            Long userId,
            Integer quizResult,
            String nickName,
            List<Characters> characters
    ){
        return new RankingTopTenResponse(userId, quizResult, nickName, characters);
    }
}
