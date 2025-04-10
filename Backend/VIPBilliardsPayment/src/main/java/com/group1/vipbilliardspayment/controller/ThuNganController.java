package com.group1.vipbilliardspayment.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group1.vipbilliardspayment.dto.request.ThuNganCreateRequest;
import com.group1.vipbilliardspayment.dto.request.ThuNganUpdateRequest;
import com.group1.vipbilliardspayment.dto.response.ApiResponse;
import com.group1.vipbilliardspayment.dto.response.ThuNganResponse;
import com.group1.vipbilliardspayment.exception.AppException;
import com.group1.vipbilliardspayment.exception.ErrorCode;
import com.group1.vipbilliardspayment.service.ThuNganService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
@RestController
@RequestMapping("/thungan")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ThuNganController {

	ThuNganService thuNganService;
	
	
	@GetMapping("/getallthungan")
	public ApiResponse<List<ThuNganResponse>> GetAlThuNgan() {
		return ApiResponse.<List<ThuNganResponse>>builder().result(thuNganService.GetAlThuNgan()).build();
	}

	@PostMapping("/timthungan")
	public ApiResponse<List<ThuNganResponse>> FindThuNganByHoTen(@RequestBody Map<String, Object> hoTen) {
		return ApiResponse.<List<ThuNganResponse>>builder()
				.result(thuNganService.FindThuNganByHoTen(hoTen.get("hoTen").toString())).build();
	}

	@PostMapping("/getthunganbytendangnhap")
	public ApiResponse<ThuNganResponse> GetThuNganByTenDangNhap(@RequestBody Map<String, Object> tenDangNhap) {
		return ApiResponse.<ThuNganResponse>builder()
				.result(thuNganService.GetThuNganByTenDangNhap(tenDangNhap.get("TenDangNhap").toString())).build();
	}

	@PostMapping("/themthungan")
	public ApiResponse<ThuNganResponse> CreateThuNgan(@RequestBody @Valid ThuNganCreateRequest thungan) {
		return ApiResponse.<ThuNganResponse>builder()
			.result(thuNganService.CreateThuNgan(thungan)) 
			.build();
	}
	
	@PutMapping("/updatethungan/{maThuNgan}")
	public ApiResponse<ThuNganResponse> UpdateThuNgan(@PathVariable Integer maThuNgan, @RequestBody @Valid ThuNganUpdateRequest request)
	{
		return ApiResponse.<ThuNganResponse>builder()
			.result(thuNganService.UpdateThuNgan(maThuNgan, request))
			.build();
	}
}
