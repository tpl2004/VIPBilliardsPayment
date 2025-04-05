package com.group1.vipbilliardspayment.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ThuNganCreateRequest {
    @NotNull(message = "FIELD_NULL")
    String hoTen;

    @NotNull(message = "FIELD_NULL")
    Date ngaySinh;

    @NotNull(message = "FIELD_NULL")
    boolean gioiTinhNu;

    @NotNull(message = "FIELD_NULL")
    @Email(message = "EMAIL_INVALID")
    String email;

    @NotNull(message = "FIELD_NULL")
    @Pattern(regexp = "^0[0-9]{9}$", message = "SODIENTHOAI_INVALID")
    String soDienThoai;

    @NotNull(message = "FIELD_NULL")
    @Pattern(regexp = "[0-9]{12}$", message = "SOCCCD_INVALID")
    String soCCCD;

    @NotNull(message = "FIELD_NULL")
    @Size(min = 3, message = "TENDANGNHAP_INVALID")
    String tenDangNhap;

    @NotNull(message = "FIELD_NULL")
    @Size(min = 3, message = "MATKHAU_INVALID")
    String matKhau;
}
