package com.group1.vipbilliardspayment.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name = "ThuNgan")
public class ThuNgan extends NguoiDung{
    private static String role = "USER";

    public static String getRole() {
        return role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaThuNgan")
    Integer maThuNgan;

    @Column(name = "HoTen")
    String hoTen;

    @Column(name = "NgaySinh")
    Date ngaySinh;

    @Column(name = "GioiTinhNu")
    boolean gioiTinhNu;

    @Column(name = "Email")
    String email;

    @Column(name = "SoDienThoai")
    String soDienThoai;

    @Column(name = "SoCCCD")
    String soCCCD;

    @OneToMany(mappedBy = "thuNgan", fetch = FetchType.LAZY)
    List<HoaDon> danhSachHoaDon;
}
