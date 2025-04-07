package com.group1.vipbilliardspayment.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "CapDoHoiVien")
public class CapDoHoiVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CapDo")
    Integer capDo;

    @Column(name = "TenCapDo")
    String tenCapDo;

    @Column(name = "SoGioChoi")
    Double soGioChoi;

    @Column(name = "UuDai")
    Double uuDai;

    @OneToMany(mappedBy = "capDo", fetch = FetchType.LAZY)
    List<HoiVien> danhSachHoiVien;
}
