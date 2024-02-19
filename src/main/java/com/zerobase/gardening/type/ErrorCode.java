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
    ALREADY_WITHDRAW_USER(HttpStatus.BAD_REQUEST, "이미 탈퇴한 회원입니다."),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "존재하지 않는 회원입니다."),
    API_ERROR(HttpStatus.BAD_GATEWAY, "API 호출에 실패하였습니다. 잠시후 다시 시도해주세요."),
    PLANT_NOT_FOUND(HttpStatus.BAD_REQUEST, "등록되지 않은 식물입니다."),
    UNREGISTERED_IN_PLANT_ENCYCLOPEDIA(HttpStatus.BAD_REQUEST, "식물도감에 등록되지 않은 식물입니다."),
    FILE_EXTENSION_ERROR(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일입니다."),
    FILE_UPLOAD_FAILURE(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다."),
    EVENT_NOT_FOUND(HttpStatus.BAD_REQUEST, "등록되지 않은 이벤트입니다."),
    EVENT_CHECKED_UPDATE_FAILURE(HttpStatus.INTERNAL_SERVER_ERROR, "변경에 실패했습니다."),
    EVENT_DATE_ERROR(HttpStatus.BAD_REQUEST, "현재시점 이전 날짜는 이벤트 등록이 불가능합니다.")
    ;


    private final HttpStatus httpStatus;
    private final String message;
}
