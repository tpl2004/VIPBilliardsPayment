package com.group1.vipbilliardspayment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.vipbilliardspayment.dto.response.MatHangResponse;
import com.group1.vipbilliardspayment.entity.MatHang;
import com.group1.vipbilliardspayment.repository.MatHangRepository;

@Service
public class MatHangService {
	@Autowired
	MatHangRepository matHang;
	
	public List<MatHang> getAllMatHang()
	{
		return matHang.findAll();
	}
	
	public MatHang themMatHang(String tenHang, Double donGia)
	{
		try {
			MatHang mh = matHang.save(new MatHang(tenHang, donGia));
			return mh;
		} catch (Exception e) {
			return null;
		}
	}
	
	public MatHang capNhapMatHang(Integer maHang, String tenHang, Double donGia)
	{
		Optional<MatHang> mh = matHang.findById(maHang);
		if(mh.isPresent())
		{
			MatHang mathang_ = mh.get();
			mathang_.setTenHang(tenHang);
			mathang_.setDonGia(donGia);
			mathang_ = matHang.save(mathang_);
			return mathang_;
		}
		return null;
	}
}
