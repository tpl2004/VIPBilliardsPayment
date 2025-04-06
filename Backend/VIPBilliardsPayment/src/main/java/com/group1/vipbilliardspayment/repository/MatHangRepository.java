package com.group1.vipbilliardspayment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group1.vipbilliardspayment.entity.MatHang;


@Repository
public interface MatHangRepository extends JpaRepository<MatHang, Integer>{

    Optional<MatHang> findByTenHang(String tenHang);
    boolean existsByTenHang(String tenHang);
}
