package com.example.blog2022.exception;

import lombok.Getter;

@Getter
public class InvalidRequest extends BaseException {

    private static final String ERROR_MESSAGE = "잘못된 요청입니다.";
    private String filedName;
    private String message;

    public InvalidRequest() {
        super(ERROR_MESSAGE);
    }

    public InvalidRequest(Throwable cause) {
        super(ERROR_MESSAGE, cause);
    }

    public InvalidRequest(String filedName, String message) {
        super(ERROR_MESSAGE);
        this.filedName = filedName;
        this.message = message;
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
