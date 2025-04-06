package com.group1.vipbilliardspayment.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HoiVienCreateRequest {
    @NotNull(message = "FIELD_NULL")
    String hoTen;

    @NotNull(message = "FIELD_NULL")
    @Email(message = "EMAIL_INVALID")
    String email;

    @NotNull(message = "FIELD_NULL")
    @Pattern(regexp = "^0[0-9]{9}$", message = "SODIENTHOAI_INVALID")
    String soDienThoai;

    @NotNull(message = "FIELD_NULL")
    @Pattern(regexp = "[0-9]{12}$", message = "SOCCCD_INVALID")
    String soCCCD;
}
