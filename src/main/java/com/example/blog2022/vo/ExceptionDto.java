package com.example.blog2022.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ExceptionDto {
    private String fieldName;
    private String defaultMessage;

    @Builder
    public ExceptionDto(String fieldName, String defaultMessage) {
        this.fieldName = fieldName;
        this.defaultMessage = defaultMessage;
    }
}
