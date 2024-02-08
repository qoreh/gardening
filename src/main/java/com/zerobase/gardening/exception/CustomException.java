package com.zerobase.gardening.exception;

import com.zerobase.gardening.type.ErrorCode;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {
    private ErrorCode errorCode;
    private String message;

    public CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }
}
