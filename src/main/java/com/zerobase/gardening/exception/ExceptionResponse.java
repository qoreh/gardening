package com.zerobase.gardening.exception;

import com.zerobase.gardening.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponse {
    private ErrorCode errorCode;
    private String message;
}
