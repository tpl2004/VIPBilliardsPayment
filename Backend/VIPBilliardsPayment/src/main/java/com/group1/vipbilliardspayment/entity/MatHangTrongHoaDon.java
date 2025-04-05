package com.group1.vipbilliardspayment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "MatHangTrongHoaDon")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@IdClass(MatHangTrongHoaDonId.class)
public class MatHangTrongHoaDon {

    @Id
    @ManyToOne
    @JoinColumn(name = "MaHang")
    MatHang matHang;

    @Id
    @ManyToOne
    @JoinColumn(name = "MaHoaDon")
    HoaDon hoaDon;
    
    @Column(name = "SoLuong")
    int soLuong;
}
