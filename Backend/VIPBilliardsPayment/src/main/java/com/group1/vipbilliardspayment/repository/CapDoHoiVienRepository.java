package com.group1.vipbilliardspayment.repository;

import com.group1.vipbilliardspayment.entity.CapDoHoiVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CapDoHoiVienRepository extends JpaRepository<CapDoHoiVien, Integer> {
    boolean existsByTenCapDo(String tenCapDo);

    boolean existsBySoGioChoi(double soGioChoi);
}
