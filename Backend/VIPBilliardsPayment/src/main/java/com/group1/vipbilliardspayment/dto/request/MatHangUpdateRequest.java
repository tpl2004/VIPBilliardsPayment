package com.group1.vipbilliardspayment.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatHangUpdateRequest {
    @NotNull(message = "FIELD_NULL")
    String tenHang;

    @NotNull(message = "FIELD_NULL")
    @Min(value = 0, message = "DONGIA_INVALID")
    Double donGia;
}
