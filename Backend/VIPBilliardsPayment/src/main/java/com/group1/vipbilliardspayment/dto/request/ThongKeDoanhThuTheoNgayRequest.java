package com.group1.vipbilliardspayment.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ThongKeDoanhThuTheoNgayRequest {
    @NotNull(message = "FIELD_NULL")
    Date ngayBatDau;

    @NotNull(message = "FIELD_NULL")
    Date ngayKetThuc;
}
