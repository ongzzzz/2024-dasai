package com.example.xiyouji.result_rank_comment.dto;

import com.example.xiyouji.type.Characters;

import java.util.List;

public record RankingDto(

        Long userId,
        Integer quizResult,
        String nickName,
        List<Characters> characters
) {
    public static RankingDto of(
            Long userId,
            Integer quizResult,
            String nickName,
            List<Characters> characters
    ){
        return new RankingDto(userId, quizResult, nickName, characters);
    }
}
