package com.group1.vipbilliardspayment.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "HoaDon")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaHoaDon")
    Integer maHoaDon;

    @Column(name = "ThoiDiemVao")
    Date thoiDiemVao;
    
    @Column(name = "ThoiDiemRa")
    Date thoiDiemRa;
    
    @Column(name = "SoGioChoi")
    double soGioChoi;
    
    @Column(name = "TrangThai")
    boolean trangThai;
    
    @Column(name = "TongTien")
    double tongTien;
    
    @ManyToOne
    @JoinColumn(name = "MaThuNgan")
    ThuNgan thuNgan;
    
    @ManyToOne
    @JoinColumn(name = "MaHoiVien")
    HoiVien hoiVien;
    
    @ManyToOne
    @JoinColumn(name = "SoBan")
    BanBida banBida;
}
