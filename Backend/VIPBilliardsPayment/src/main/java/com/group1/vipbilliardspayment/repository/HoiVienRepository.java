package com.group1.vipbilliardspayment.repository;

import com.group1.vipbilliardspayment.entity.HoiVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoiVienRepository extends JpaRepository<HoiVien, Integer> {
}
