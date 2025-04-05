package com.group1.vipbilliardspayment.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BanBidaCreateRequest {
    @NotNull(message = "FIELD_NULL")
    Integer LoaiBan;
}
