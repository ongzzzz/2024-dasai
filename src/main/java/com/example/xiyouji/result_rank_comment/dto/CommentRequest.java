package com.example.xiyouji.result_rank_comment.dto;

public record CommentRequest(
        String comment
) {
    public static CommentRequest of(String comment){
        return new CommentRequest(comment);
    }
}
