package com.group1.vipbilliardspayment.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group1.vipbilliardspayment.entity.BanBida;
import com.group1.vipbilliardspayment.entity.LoaiBan;
import com.group1.vipbilliardspayment.entity.MatHang;
import com.group1.vipbilliardspayment.service.BanBidaService;
import com.group1.vipbilliardspayment.service.LoaiBanService;
import com.group1.vipbilliardspayment.service.MatHangService;

@RestController
@RequestMapping
public class ChuQuan {
	@Autowired
	MatHangService matHang;
	
	@Autowired
	BanBidaService banBida;
	
	@Autowired
	LoaiBanService loaiBan;
	
	@GetMapping("/getallmathang")
	List<MatHang> getAllMatHang()
	{
		System.out.println(matHang.getAllMatHang().size());
		return matHang.getAllMatHang();
	}
	
	@PostMapping("/themmathang")
	MatHang themMatHang(@RequestBody Map<String, Object> data)
	{
		System.out.println(data);
		return matHang.themMatHang("ga nuong", 10000.0);
//		return null;
	}
//	
//	@GetMapping("/capnhatmathang")
//	MatHang capNhapMatHang()
//	{
//		System.out.println(matHang.getAllMatHang().size());
//		return matHang.capNhapMatHang(1, "ga nuong", 10000.0);
//	}
	
	@GetMapping("/getallbanbida")
	List<BanBida> getAllBanBida()
	{
		return banBida.getAllBanBida();
	}
	
//	@GetMapping("/capnhapbanbida")
//	BanBida capNhapBanBida()
//	{
//		return banBida.themBanBida(1);
//	}
//	@GetMapping("/xoabanbida")
//	BanBida xoaBanBida()
//	{
//		return banBida.xoaBanBida(1);
//	}
	
	@GetMapping("/getallloaiban")
	List<LoaiBan> getAllLoaiBan()
	{
		return loaiBan.getAllLoaiBan();
	}
	
//	@GetMapping("/capnhaploaiban")
//	LoaiBan capNhapLoaiBan()
//	{
//		return loaiBan.capNhapLoaiBan(1, "Vip pro", 10000.0);
//	}
//	
//	@GetMapping("/themloaiban")
//	LoaiBan themLoaiBan()
//	{
//		return loaiBan.themLoaiBan("sieu vip", 1000.0);
//	}
}
