package com.example.xiyouji.exception.impl;

import com.example.xiyouji.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor
public enum EnumErrorCode implements ErrorCode {
    ENUM_NOT_FOUNDED(HttpStatus.BAD_REQUEST, "ENUM NOT FOUNDED"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
