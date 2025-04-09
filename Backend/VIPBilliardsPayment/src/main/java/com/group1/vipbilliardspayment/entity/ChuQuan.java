package com.group1.vipbilliardspayment.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name = "ChuQuan")
public class ChuQuan extends NguoiDung{
    private static String role = "ADMIN";

    public static String getRole() {
        return role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaChuQuan")
    Integer maChuQuan;
}
