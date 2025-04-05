package com.group1.vipbilliardspayment.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CapDoHoiVienCreateRequest {
    @NotNull(message = "FIELD_NULL")
    String tenCapDo;

    @NotNull(message = "FIELD_NULL")
    @Min(value = 0, message = "SOGIOCHOI_INVALID")
    double soGioChoi;

    @NotNull(message = "FIELD_NULL")
    @Min(value = 0, message = "UUDAI_INVALID")
    double uuDai;
}
