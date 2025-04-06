package com.group1.vipbilliardspayment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group1.vipbilliardspayment.dto.request.BanBidaCreateRequest;
import com.group1.vipbilliardspayment.dto.response.ApiResponse;
import com.group1.vipbilliardspayment.dto.response.BanBidaResponse;
import com.group1.vipbilliardspayment.exception.AppException;
import com.group1.vipbilliardspayment.exception.ErrorCode;
import com.group1.vipbilliardspayment.service.BanBidaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/banbida")
public class BanBidaController {
	@Autowired
	BanBidaService banBida;
	
	@GetMapping("/getallbanbida")
	ApiResponse<List<BanBidaResponse>> GetAllBanBida() {
		return ApiResponse.<List<BanBidaResponse>>builder().result(banBida.getAllBanBida()).build();
	}

	@GetMapping("/getallbanbidachuaxoa")
	ApiResponse<List<BanBidaResponse>> GetAllBanBidaChuaXoa() {
		return ApiResponse.<List<BanBidaResponse>>builder().result(banBida.getAllBanBidaChuaXoa()).build();
	}

	@PostMapping("/thembanbida")
	ApiResponse<BanBidaResponse> themBanBida(@RequestBody @Valid BanBidaCreateRequest banbida) {
		
		try {
			return ApiResponse.<BanBidaResponse>builder().result(banBida.themBanBida(banbida)).build();
		} catch (AppException ae) {
			return new ApiResponse<>(ae.getErrorCode().getCode(), ae.getErrorCode().getMessage(), null);
		} catch (Exception e) {
			return new ApiResponse<>(ErrorCode.UNKNOWN_ERROR.getCode(), ErrorCode.UNKNOWN_ERROR.getMessage(), null);
		}

	}

	@PostMapping("/xoabanbida/{SoBan}")
	ApiResponse<BanBidaResponse> xoaBanBida(@PathVariable String SoBan) {
		
		try {
			Integer soBan = Integer.valueOf(SoBan);
			return ApiResponse.<BanBidaResponse>builder().result(banBida.xoaBanBida(soBan)).build();
		} catch (AppException ae) {
			return new ApiResponse<>(ae.getErrorCode().getCode(), ae.getErrorCode().getMessage(), null);
		} catch (Exception e) {
			return new ApiResponse<>(ErrorCode.UNKNOWN_ERROR.getCode(), ErrorCode.UNKNOWN_ERROR.getMessage(), null);
		}

	}
}
