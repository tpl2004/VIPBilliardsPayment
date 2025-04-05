package com.group1.vipbilliardspayment.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequest {
    @NotNull(message = "FIELD_NULL")
    @Size(min = 3, message = "TENDANGNHAP_INVALID")
    String tenDangNhap;

    @NotNull(message = "FIELD_NULL")
    @Size(min = 3, message = "MATKHAU_INVALID")
    String matKhau;
}
