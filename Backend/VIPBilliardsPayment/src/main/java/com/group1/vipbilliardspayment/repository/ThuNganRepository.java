package com.group1.vipbilliardspayment.repository;

import com.group1.vipbilliardspayment.entity.ChuQuan;
import com.group1.vipbilliardspayment.entity.ThuNgan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThuNganRepository extends JpaRepository<ThuNgan, Integer> {
    public Optional<ThuNgan> findByTenDangNhap(String tenDangNhap);

}
