package com.example.blog2022.exception;

import lombok.Getter;

@Getter
public enum BaseExceptionType {
    ERROR_400("400", "해당 아이디 데이터가 존재하지 않습니다."),
    ERROR_404("404", "잘못된 요청입니다.")
    ;

    private String code;
    private String message;

    BaseExceptionType(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
