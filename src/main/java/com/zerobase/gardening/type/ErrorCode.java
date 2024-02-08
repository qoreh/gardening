package com.zerobase.gardening.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    ALREADY_EXIST_USER(HttpStatus.BAD_REQUEST, "이미 존재하는 회원입니다."),
    INVALID_EMAIL(HttpStatus.BAD_REQUEST, "잘못된 이메일입니다."),
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    ALREADY_WITHDRAW_USER(HttpStatus.BAD_REQUEST, "이미 탈퇴한 회원입니다.")
    ;


    private final HttpStatus httpStatus;
    private final String message;
}
