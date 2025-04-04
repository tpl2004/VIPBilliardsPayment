package com.group1.vipbilliardspayment.repository;

import com.group1.vipbilliardspayment.entity.ChuQuan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChuQuanRepository extends JpaRepository<ChuQuan, Integer> {
}
