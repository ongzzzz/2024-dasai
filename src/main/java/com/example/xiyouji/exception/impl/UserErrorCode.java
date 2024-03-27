package com.example.xiyouji.exception.impl;

import com.example.xiyouji.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {
    OK(HttpStatus.OK, "success"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않은 회원입니다."),
    USER_RANKING_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않은 회원 랭킹입니다."),
    ALREADY_WROTE_COMMENT(HttpStatus.BAD_REQUEST, "한 계정당 1회 작성 가능합니다."),
    ALREADY_EXIST_USER(HttpStatus.BAD_REQUEST, "이미 존재하는 닉네임입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
