package com.zerobase.gardening.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ExceptionResponse handleAccountException(CustomException e) {
        return new ExceptionResponse(e.getErrorCode(), e.getMessage());
    }
}
