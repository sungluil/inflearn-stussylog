package com.example.blog2022.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorDto {
    private String errorCode;
    private String errorMessage;
    private Set<ExceptionDto> exceptionDtoSet = new HashSet<>();
    public void addValid(ExceptionDto exceptionDto) {
        this.exceptionDtoSet.add(exceptionDto);
    }

    @Builder
    public ErrorDto(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
