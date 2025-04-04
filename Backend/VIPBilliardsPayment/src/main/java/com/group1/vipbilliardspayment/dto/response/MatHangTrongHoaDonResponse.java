package com.group1.vipbilliardspayment.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatHangTrongHoaDonResponse {

    Integer maHang;
    String tenHang;
    Integer maHoaDon;
    double donGia;
    int soLuong;
}
