package com.group1.vipbilliardspayment.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.vipbilliardspayment.dto.request.BanBidaCreateRequest;
import com.group1.vipbilliardspayment.dto.response.BanBidaResponse;
import com.group1.vipbilliardspayment.entity.BanBida;
import com.group1.vipbilliardspayment.entity.LoaiBan;
import com.group1.vipbilliardspayment.exception.AppException;
import com.group1.vipbilliardspayment.exception.ErrorCode;
import com.group1.vipbilliardspayment.mapper.BanBidaMapper;
import com.group1.vipbilliardspayment.mapper.DataMapper;
import com.group1.vipbilliardspayment.repository.BanBidaRepository;
import com.group1.vipbilliardspayment.repository.LoaiBanRepository;

import lombok.Data;

@Service
public class BanBidaService {

	@Autowired
	BanBidaRepository banBida;

	@Autowired
	LoaiBanRepository loaiBan;

	@Autowired
	DataMapper dataMapper;

	public List<BanBidaResponse> getAllBanBida() {
		List<BanBida> lstBanBida = banBida.findAll();
		return lstBanBida.stream().map(dataMapper::toBanBidaResponse).collect(Collectors.toList());
	}

//	
	public List<BanBidaResponse> getAllBanBidaChuaXoa() {
		List<BanBida> lstBanBida = banBida.findAll();
		return lstBanBida.stream().filter(ban_ -> ban_.getTrangThai() != -1).map(dataMapper::toBanBidaResponse)
				.collect(Collectors.toList());
	}

	public BanBidaResponse themBanBida(BanBidaCreateRequest loaiban_) {

		Optional<LoaiBan> lb = loaiBan.findById(loaiban_.getLoaiBan());
		if (lb.isPresent()) {
			try {
				BanBida ban_ = new BanBida();
				ban_.setTrangThai(0);
				ban_.setLoaiBan(lb.get());
				ban_ = banBida.save(ban_);
				return dataMapper.toBanBidaResponse(ban_);
			} catch (Exception e) {
				throw new AppException(ErrorCode.CREATE_FAILED);
			}

		} else {
			throw new AppException(ErrorCode.LOAIBAN_NOT_FOUND);
		}

	}

	public BanBidaResponse xoaBanBida(Integer soBan) {
		Optional<BanBida> ban = banBida.findById(soBan);
		if (ban.isPresent()) {
			try {
				BanBida banbida_ = ban.get();
				banbida_.setTrangThai(0);
				banbida_ = banBida.save(banbida_);
				return dataMapper.toBanBidaResponse(banbida_);
			} catch (Exception e) {
				throw new AppException(ErrorCode.DELETE_FAILD);
			}

		} else {
			throw new AppException(ErrorCode.SOBAN_NOT_FOUND);
		}
	}

}
