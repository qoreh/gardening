package com.zerobase.gardening.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
    USER_STATUS_ING("ING"),
    USER_STATUS_WITHDRAW("WITHDRAW");

    private final String code;
}
