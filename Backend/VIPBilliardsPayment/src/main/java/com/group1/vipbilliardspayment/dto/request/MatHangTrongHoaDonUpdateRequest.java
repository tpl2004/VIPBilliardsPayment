package com.group1.vipbilliardspayment.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatHangTrongHoaDonUpdateRequest {
    @NotNull(message = "FIELD_NULL")
    Integer maHang;

    @NotNull(message = "FIELD_NULL")
    Integer maHoaDon;

    @NotNull(message = "FIELD_NULL")
    @Min(value = 0, message = "SOLUONG_INVALID")
    int soLuong;
}
