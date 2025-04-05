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
    SOLUONG_INVALID(7, "SoLuong must be greater than or equal to 0"),
    CAPDOHOIVIENLIST_EMPTY(9, "CapDoHoiVien list is empty"),
    SOCCCD_EXISTED(11, "SoCCCD is existed"),
    EMAIL_INVALID(2, "Email is invalid"),
    SODIENTHOAI_INVALID(4, "The length of SoDienThoai is 10 and starting by 0"),
    SOCCCD_INVALID(6, "The length of SoCCCD is 12"),
    TENDANGNHAP_INVALID(8, "TenDangNhap must be at least 3 characters"),
    MATKHAU_INVALID(10, "MatKhau must be at least 3 characters"),
    DONGIA_INVALID(12, "DonGia must be greater than or equal to 0"),
    MATHANG_EXISTED(473, "Mat hang existed"),
    MATHANG_NOTEXIST(512, "Mat hang does not existed"),
    TENCAPDO_EXISTED(14, "TenCapDo is existed"),
    SOGIOCHOI_EXISTED(16, "SoGioChoi is existed"),
    ;

    int code;
    String message;
}
