package com.group1.vipbilliardspayment.repository;

import com.group1.vipbilliardspayment.entity.ChuQuan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChuQuanRepository extends JpaRepository<ChuQuan, Integer> {
    public Optional<ChuQuan> findByTenDangNhap(String tenDangNhap);
}
