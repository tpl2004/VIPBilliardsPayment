package com.group1.vipbilliardspayment.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    FIELD_NULL(1, "Field must be not null"),
    SOGIOCHOI_INVALID(3, "SoGioChoi must be greater than or equal to 0"),
    UUDAI_INVALID(5, "UuDai must be greater than or equal to 0"),
    SOLUONG_INVALID(7, "SoLuong must be greater than or equal to 0"),
    EMAIL_INVALID(2, "Email is invalid"),
    SODIENTHOAI_INVALID(4, "The length of SoDienThoai is 10 and starting by 0"),
    SOCCCD_INVALID(6, "The length of SoCCCD is 12"),
    TENDANGNHAP_INVALID(8, "TenDangNhap must be at least 3 characters"),
    MATKHAU_INVALID(10, "MatKhau must be at least 3 characters"),
    DONGIA_INVALID(12, "DonGia must be greater than or equal to 0"),
    ;

    int code;
    String message;
}
