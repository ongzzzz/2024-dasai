package com.example.xiyouji.result_rank_comment.dto;

public record TranslateResponse(
        String content
) {
    public static TranslateResponse of(String content){
        return new TranslateResponse(content);
    }
}
