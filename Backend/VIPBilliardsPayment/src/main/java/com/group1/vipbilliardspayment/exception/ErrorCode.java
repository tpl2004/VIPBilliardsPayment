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
    CAPDOHOIVIENLIST_EMPTY(9, "CapDoHoiVien list is empty"),
    SOCCCD_EXISTED(11, "SoCCCD is existed"),
    EMAIL_INVALID(2, "Email is invalid"),
    SODIENTHOAI_INVALID(4, "The length of SoDienThoai is 10 and starting by 0"),
    SOCCCD_INVALID(6, "The length of SoCCCD is 12"),
    TENDANGNHAP_INVALID(8, "TenDangNhap must be at least 3 characters"),
    MATKHAU_INVALID(10, "MatKhau must be at least 3 characters"),
    DONGIA_INVALID(12, "DonGia must be greater than or equal to 0"),
    MATHANG_EXISTED(473, "Mat hang existed"),
    MATHANG_NOTEXIST(512, "Mat hang does not exist"),
    TENCAPDO_EXISTED(14, "TenCapDo is existed"),
    SOGIOCHOI_EXISTED(16, "SoGioChoi is existed"),
    LOAIBAN_EXISTED(555, "Loai ban existed"),
    LOAIBAN_NOTEXIST(492, "Loai ban does not exist"),
    TENLOAIBAN_EXISTED(390, "Ten loai ban existed"),
	THUNGAN_NOT_FOUND(50, "Thu Ngan not found."),
    UPDATE_FAILED(51, "Update failed."),
	CREATE_FAILED(52, "Create failed."),
	SOBAN_NOT_FOUND(53, "So Ban not found."),
	LOAIBAN_NOT_FOUND(54, "Loai Ban not found."),
	DELETE_FAILD(55, "Delete failed."),
	TENDANGNHAP_ALREADY_EXISTS(56, "Username already exists"),
	SOCCCD_ALREADY_EXISTS(57, "SoCCCD already exists"),
	UNKNOWN_ERROR(99, "An unknown error occurred"),
    BANBIDA_NOTEXIST(777, "Ban bida not exist"),
    BANBIDA_NOT_AVAILABLE(482, "Ban bida is not available"),
    HOADON_NOT_EXIST(888, "Hoa don is not exist"),
    HOIVIEN_NOT_EXISTED(13, "Hoi vien is not existed")
    ;

    int code;
    String message;
}
