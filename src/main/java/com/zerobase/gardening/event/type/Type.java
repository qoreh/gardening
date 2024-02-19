package com.zerobase.gardening.event.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Type {
    WATERING("급수"),
    REPOTTING("분갈이"),
    TONIC("영양제 투여"),
    PRUNING("가지치기"),
    ETC("기타");

    private final String code;

}
