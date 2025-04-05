package com.group1.vipbilliardspayment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.vipbilliardspayment.entity.BanBida;
import com.group1.vipbilliardspayment.entity.LoaiBan;
import com.group1.vipbilliardspayment.repository.BanBidaRepository;
import com.group1.vipbilliardspayment.repository.LoaiBanRepository;

@Service

public class BanBidaService {
	@Autowired
	BanBidaRepository banBida;
	
	@Autowired
	LoaiBanRepository loaiBan;
	
	public List<BanBida> getAllBanBida()
	{
		return banBida.findAll();
	}
	
	public BanBida themBanBida(Integer loaiBan_)
	{
		
		Optional<LoaiBan> lb = loaiBan.findById(loaiBan_);
		if(lb.isPresent())
		{
			BanBida ban_ = new BanBida(0, lb.get());
			ban_ = banBida.save(ban_);
			return ban_;
		}
		return null;
	}
	
	public BanBida xoaBanBida(Integer soBan)
	{
		Optional<BanBida> ban = banBida.findById(soBan);
		if(ban.isPresent())
		{
			BanBida banbida_ = ban.get();
			banbida_.setTrangThai(-1);
			banbida_ = banBida.save(banbida_);
			return banbida_;
		}
		return null;
	}
}
