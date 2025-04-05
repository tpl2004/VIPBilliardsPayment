package com.group1.vipbilliardspayment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group1.vipbilliardspayment.dto.response.BanBidaResponse;
import com.group1.vipbilliardspayment.entity.BanBida;
@Repository
public interface BanBidaRepository extends JpaRepository<BanBida, Integer>{
	@Query(value = "select ban.SoBan, ban.TrangThai, lb.TenLoai, lb.DonGia from BanBida ban join LoaiBan lb on ban.LoaiBan = lb.LoaiBan", nativeQuery = true)
	List<BanBidaResponse> GetAllBanBida();
	
	@Query(value = "select ban.SoBan, ban.TrangThai, lb.TenLoai, lb.DonGia from BanBida ban join LoaiBan lb on ban.LoaiBan = lb.LoaiBan where ban.TrangThai <> -1", nativeQuery = true)
	List<BanBidaResponse> GetAllBanBidaChuaXoa();
}
