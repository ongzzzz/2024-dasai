package com.example.xiyouji.result_rank_comment.dto;

public record CommentResponse(
        String nickName,
        String content
) {
    public static CommentResponse of(
            String nickName,
            String content
    ){
        return new CommentResponse(nickName, content);
    }
}
