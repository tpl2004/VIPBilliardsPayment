package com.group1.vipbilliardspayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group1.vipbilliardspayment.entity.BanBida;
@Repository
public interface BanBidaRepository extends JpaRepository<BanBida, Integer>{

	
}
