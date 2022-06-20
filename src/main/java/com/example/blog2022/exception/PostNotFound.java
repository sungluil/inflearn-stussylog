package com.example.blog2022.exception;

public class PostNotFound extends BaseException {

    private static final String ERROR_MESSAGE = "잘못된 아이디 입니다.";

    public PostNotFound() {
        super(ERROR_MESSAGE);
    }

    public PostNotFound(Throwable cause) {
        super(ERROR_MESSAGE, cause);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
