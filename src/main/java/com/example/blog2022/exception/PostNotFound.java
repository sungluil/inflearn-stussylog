package com.example.blog2022.exception;

import lombok.Getter;

@Getter
public class PostNotFound extends BaseException {

    private BaseExceptionType exceptionType;

    public PostNotFound(String message) {
        super(message);
    }

    public PostNotFound(BaseExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }
}
