package com.group1.vipbilliardspayment.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity

@Table(name = "BanBida")

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BanBida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SoBan")
    Integer soBan;

    @Column(name = "TrangThai")
    Integer trangThai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loaiBan")
    LoaiBan loaiBan;

    @OneToMany(mappedBy = "banBida", fetch = FetchType.LAZY)
    List<HoaDon> danhSachHoaDon;

}