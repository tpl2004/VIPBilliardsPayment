package com.group1.vipbilliardspayment.dto.response;

import java.util.Date;

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
public class HoaDonResponse {

    Integer maHoaDon;
    Date thoiDiemVao;
    Date thoiDiemRa;
    Double soGioChoi;
    Integer soBan;
    String tenLoaiBan;
    double donGia;
    String tenThuNgan;
    String tenHoiVien;
    String tenCapDo;
    Double uuDai;
    Double tongTien;
}
