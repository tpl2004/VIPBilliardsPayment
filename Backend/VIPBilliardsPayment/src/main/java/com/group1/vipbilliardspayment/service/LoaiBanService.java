package com.group1.vipbilliardspayment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.vipbilliardspayment.entity.LoaiBan;
import com.group1.vipbilliardspayment.repository.LoaiBanRepository;

@Service
public class LoaiBanService {
	@Autowired
	LoaiBanRepository loaiBan;
	
	public List<LoaiBan> getAllLoaiBan()
	{
		return loaiBan.findAll();
	}
	
	public LoaiBan themLoaiBan(String tenLoai, Double donGia)
	{
		try {
			LoaiBan lb = new LoaiBan(tenLoai, donGia);
			lb = loaiBan.save(lb);
			return lb;
		} catch (Exception e) {
			return null;
		}
	}
	public LoaiBan capNhapLoaiBan(Integer soBan, String tenLoai, Double donGia)
	{
		Optional<LoaiBan> lb = loaiBan.findById(soBan);
		if(lb.isPresent())
		{
			LoaiBan loaiban_ = lb.get();
			loaiban_.setTenLoai(tenLoai);
			loaiban_.setDonGia(donGia);
			loaiban_ = loaiBan.save(loaiban_);
			return loaiban_;
		}
		return null;
	}
}
