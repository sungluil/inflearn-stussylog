package com.example.blog2022.exception;

import lombok.Getter;

@Getter
public class InvalidRequest extends BaseException {

    private BaseExceptionType exceptionType;

    public InvalidRequest(String message) {
        super(message);
    }

    public InvalidRequest(BaseExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }

    public InvalidRequest(String message, Throwable cause) {
        super(message, cause);
    }
}
