package com.group1.vipbilliardspayment.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.group1.vipbilliardspayment.dto.request.BanBidaCreateRequest;
import com.group1.vipbilliardspayment.dto.response.BanBidaResponse;
import com.group1.vipbilliardspayment.entity.BanBida;
import com.group1.vipbilliardspayment.entity.LoaiBan;
import com.group1.vipbilliardspayment.exception.AppException;
import com.group1.vipbilliardspayment.exception.ErrorCode;
import com.group1.vipbilliardspayment.mapper.BanBidaMapper;
import com.group1.vipbilliardspayment.repository.BanBidaRepository;
import com.group1.vipbilliardspayment.repository.LoaiBanRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BanBidaService {

	BanBidaRepository banBidaRepository;

	LoaiBanRepository loaiBanRepository;

	BanBidaMapper banBidaMapper;

	public List<BanBidaResponse> getAllBanBida() {
		List<BanBida> lstBanBida = banBidaRepository.findAll();
		return lstBanBida.stream().map(banBidaMapper::toBanBidaResponse).collect(Collectors.toList());
	}

	public List<BanBidaResponse> getAllBanBidaChuaXoa() {
		List<BanBida> lstBanBida = banBidaRepository.findAll();
		return lstBanBida.stream().filter(ban_ -> ban_.getTrangThai() != -1).map(banBidaMapper::toBanBidaResponse)
				.collect(Collectors.toList());
	}

	public BanBidaResponse themBanBida(BanBidaCreateRequest loaiban_) {

		Optional<LoaiBan> lb = loaiBanRepository.findById(loaiban_.getLoaiBan());
		if (lb.isPresent()) {
			try {
				BanBida ban_ = new BanBida();
				ban_.setTrangThai(0);
				ban_.setLoaiBan(lb.get());
				ban_ = banBidaRepository.save(ban_);
				return banBidaMapper.toBanBidaResponse(ban_);
			} catch (Exception e) {
				throw new AppException(ErrorCode.CREATE_FAILED);
			}

		} else {
			throw new AppException(ErrorCode.LOAIBAN_NOT_FOUND);
		}

	}

	public BanBidaResponse xoaBanBida(Integer soBan) {
		Optional<BanBida> ban = banBidaRepository.findById(soBan);
		if (ban.isPresent()) {
			try {
				BanBida banbida_ = ban.get();
				banbida_.setTrangThai(0);
				banbida_ = banBidaRepository.save(banbida_);
				return banBidaMapper.toBanBidaResponse(banbida_);
			} catch (Exception e) {
				throw new AppException(ErrorCode.DELETE_FAILD);
			}

		} else {
			throw new AppException(ErrorCode.SOBAN_NOT_FOUND);
		}
	}

}
