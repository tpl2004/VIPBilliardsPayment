package com.group1.vipbilliardspayment.entity;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "HoiVien")
public class HoiVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaHoiVien")
    Integer maHoiVien;

    @Column(name = "HoTen")
    String hoTen;

    @Column(name = "Email")
    String email;

    @Column(name = "SoDienThoai")
    String soDienThoai;

    @Column(name = "SoCCCD")
    String soCCCD;

    @Column(name = "SoGioChoi")
    double soGioChoi;

    @Column(name = "NgayDangKy")
    Date ngayDangKy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CapDo")
    CapDoHoiVien capDo;
}
