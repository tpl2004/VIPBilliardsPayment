package com.group1.vipbilliardspayment.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "MatHang")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaHang")
    Integer maHang;

    @Column(name = "TenHang", length = 100)
    String tenHang;

    @Column(name = "DonGia")
    Double donGia;

    @OneToMany(mappedBy = "matHang", fetch = FetchType.LAZY)
    List<MatHangTrongHoaDon> danhSachMatHangTrongHoaDon;
}
