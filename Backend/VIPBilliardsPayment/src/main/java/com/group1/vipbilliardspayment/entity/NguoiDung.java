package com.group1.vipbilliardspayment.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class NguoiDung {
    @Column(name = "TenDangNhap")
    String tenDangNhap;

    @Column(name = "MatKhau")
    String matKhau;
}
