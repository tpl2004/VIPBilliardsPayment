package com.group1.vipbilliardspayment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MatHang")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MatHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaHang")
    private Integer maHang;

    @Column(name = "TenHang", length = 100)
    private String tenHang;

    @Column(name = "DonGia")
    private Double donGia;

}
