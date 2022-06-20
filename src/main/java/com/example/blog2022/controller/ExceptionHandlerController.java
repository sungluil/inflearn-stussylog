package com.example.blog2022.controller;

import com.example.blog2022.exception.BaseException;
import com.example.blog2022.vo.ErrorDto;
import com.example.blog2022.vo.ExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity errorHandler(MethodArgumentNotValidException e) {
        ErrorDto errorDto = ErrorDto.builder()
                .errorCode("400")
                .errorMessage("잘못된 요청입니다.").build();
        for (FieldError error : e.getFieldErrors()) {
            ExceptionDto exceptionDto = ExceptionDto.builder()
                    .fieldName(error.getField())
                    .defaultMessage(error.getDefaultMessage())
                    .build();
            errorDto.addValid(exceptionDto);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity baseException(BaseException e) {
        ErrorDto errorDto = ErrorDto.builder()
                        .errorCode(e.getExceptionType().getCode())
                        .errorMessage(e.getMessage()).build();

        return ResponseEntity.status(Integer.valueOf(e.getExceptionType().getCode())).body(errorDto);
    }

}
