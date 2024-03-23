package com.example.xiyouji.exception.impl;

import com.example.xiyouji.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor
public enum StoryErrorCode implements ErrorCode {

    STORY_NOT_EXIST(HttpStatus.BAD_REQUEST, "Story Not Exist"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
