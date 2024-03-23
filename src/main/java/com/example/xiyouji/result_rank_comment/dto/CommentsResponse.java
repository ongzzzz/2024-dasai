package com.example.xiyouji.result_rank_comment.dto;

public record CommentsResponse(
        String nickName,
        String content
) {
    public static CommentsResponse of(
            String nickName,
            String content
    ){
        return new CommentsResponse(nickName, content);
    }
}
