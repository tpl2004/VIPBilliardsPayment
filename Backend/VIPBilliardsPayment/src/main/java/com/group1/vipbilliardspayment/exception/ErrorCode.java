package com.group1.vipbilliardspayment.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    FIELD_NULL(1, "Field must be not null"),
    SOGIOCHOI_INVALID(3, "SoGioChoi must be greater than or equal to 0"),
    UUDAI_INVALID(5, "UuDai must be greater than or equal to 0"),
    SOLUONG_INVALID(7, "SoLuong must be greater than or equal to 0")
    ;
    int code;
    String message;
}
