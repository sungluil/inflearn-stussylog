package com.example.blog2022.exception;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {

    private BaseExceptionType exceptionType;

    public BaseException(String message) {
        super(message);
    }

    public BaseException(BaseExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

//    public abstract int getStatusCode();
}
