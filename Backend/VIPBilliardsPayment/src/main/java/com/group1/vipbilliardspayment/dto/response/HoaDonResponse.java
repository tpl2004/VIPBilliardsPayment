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

    String maHoaDon;
    Date thoiDiemVao;
    Date thoiDiemRa;
    double soGioChoi;
    Integer soBan;
    String tenLoaiBan;
    double donGia;
    String tenThuNgan;
    String tenHoiVien;
    String tenCapDo;
    double uuDai;
    double tongTien;
}
