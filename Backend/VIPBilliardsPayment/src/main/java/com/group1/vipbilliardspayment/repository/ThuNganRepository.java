package com.group1.vipbilliardspayment.repository;

import com.group1.vipbilliardspayment.dto.response.ThuNganResponse;
import com.group1.vipbilliardspayment.entity.ThuNgan;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ThuNganRepository extends JpaRepository<ThuNgan, Integer> {
	
	
}
