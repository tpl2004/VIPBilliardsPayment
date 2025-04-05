package com.group1.vipbilliardspayment.repository;

import com.group1.vipbilliardspayment.entity.HoiVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HoiVienRepository extends JpaRepository<HoiVien, Integer> {
    public boolean existsBySoCCCD(String soCCCD);
}
