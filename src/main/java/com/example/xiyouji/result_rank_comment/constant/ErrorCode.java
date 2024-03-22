package com.example.xiyouji.result_rank_comment.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    OK(HttpStatus.OK, "success"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않은 회원입니다."),
    USER_RANKING_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않은 회원 랭킹입니다."),
    ALREADY_WROTE_COMMENT(HttpStatus.BAD_REQUEST, "한 계정당 1회 작성 가능합니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
