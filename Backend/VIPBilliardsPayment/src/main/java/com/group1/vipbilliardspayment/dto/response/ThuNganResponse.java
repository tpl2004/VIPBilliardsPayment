package com.group1.vipbilliardspayment.dto.response;

import java.util.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ThuNganResponse {
    Integer maThuNgan;
    String hoTen;
    Date ngaySinh;
    boolean gioiTinhNu;
    String email;
    String soDienThoai;
    String soCCCD;
}
