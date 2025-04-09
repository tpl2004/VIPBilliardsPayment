package com.group1.vipbilliardspayment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.group1.vipbilliardspayment.dto.request.BanBidaCreateRequest;
import com.group1.vipbilliardspayment.dto.response.ApiResponse;
import com.group1.vipbilliardspayment.dto.response.BanBidaResponse;
import com.group1.vipbilliardspayment.exception.AppException;
import com.group1.vipbilliardspayment.exception.ErrorCode;
import com.group1.vipbilliardspayment.service.BanBidaService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/banbida")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BanBidaController {

	BanBidaService banBidaService;
	
	@GetMapping("/getallbanbida")
	ApiResponse<List<BanBidaResponse>> GetAllBanBida() {
		return ApiResponse.<List<BanBidaResponse>>builder().result(banBidaService.getAllBanBida()).build();
	}

	@GetMapping("/getallbanbidachuaxoa")
	ApiResponse<List<BanBidaResponse>> GetAllBanBidaChuaXoa() {
		return ApiResponse.<List<BanBidaResponse>>builder().result(banBidaService.getAllBanBidaChuaXoa()).build();
	}

	@PostMapping("/thembanbida")
	ApiResponse<BanBidaResponse> themBanBida(@RequestBody @Valid BanBidaCreateRequest banbida) {
		return ApiResponse.<BanBidaResponse>builder()
			.result(banBidaService.themBanBida(banbida))
			.build();
	}

	@PutMapping("/xoabanbida/{soBan}")
	ApiResponse<BanBidaResponse> xoaBanBida(@PathVariable(name = "soBan") Integer soBan) {
		return ApiResponse.<BanBidaResponse>builder()
			.result(banBidaService.xoaBanBida(soBan))
			.build();
	}
}
