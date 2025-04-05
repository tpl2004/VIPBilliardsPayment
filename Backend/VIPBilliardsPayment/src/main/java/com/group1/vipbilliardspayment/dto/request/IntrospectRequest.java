package com.group1.vipbilliardspayment.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IntrospectRequest {
    @NotNull(message = "FIELD_NULL")
    String token;
}
