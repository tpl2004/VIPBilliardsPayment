package com.group1.vipbilliardspayment.repository;

import com.group1.vipbilliardspayment.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group1.vipbilliardspayment.entity.MatHangTrongHoaDon;
import com.group1.vipbilliardspayment.entity.MatHangTrongHoaDonId;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatHangTrongHoaDonRepository extends JpaRepository<MatHangTrongHoaDon, MatHangTrongHoaDonId> {
    public List<MatHangTrongHoaDon> findByHoaDon(HoaDon hoaDon);
}
