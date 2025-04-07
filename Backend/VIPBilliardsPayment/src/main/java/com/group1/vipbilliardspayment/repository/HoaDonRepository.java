package com.group1.vipbilliardspayment.repository;

import com.group1.vipbilliardspayment.entity.BanBida;
import com.group1.vipbilliardspayment.entity.HoiVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group1.vipbilliardspayment.entity.HoaDon;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {
    public boolean existsByMaHoaDon(Integer maHoaDon);

    public List<HoaDon> findByThoiDiemVao(Date createdDate);

    public List<HoaDon> findByBanBida(BanBida banBida);
}
