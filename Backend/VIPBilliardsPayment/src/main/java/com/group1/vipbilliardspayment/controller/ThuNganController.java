package com.group1.vipbilliardspayment.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@RestController
@RequestMapping("/thungan")
public class ThuNganController {
	@Autowired
	ThuNganService thuNgan;
	
	
	@GetMapping("/getallthungan")
	ApiResponse<List<ThuNganResponse>> GetAlThuNgan() {
		return ApiResponse.<List<ThuNganResponse>>builder().result(thuNgan.GetAlThuNgan()).build();
	}

	@PostMapping("/timthungan")
	ApiResponse<List<ThuNganResponse>> FindThuNganByHoTen(@RequestBody Map<String, Object> hoTen) {
		return ApiResponse.<List<ThuNganResponse>>builder()
				.result(thuNgan.FindThuNganByHoTen(hoTen.get("HoTen").toString())).build();
	}

	@PostMapping("/getthunganbytendangnhap")
	ApiResponse<ThuNganResponse> GetThuNganByTenDangNhap(@RequestBody Map<String, Object> tenDangNhap) {
		return ApiResponse.<ThuNganResponse>builder()
				.result(thuNgan.GetThuNganByTenDangNhap(tenDangNhap.get("TenDangNhap").toString())).build();
	}

	@PostMapping("/themthungan")
	ApiResponse<ThuNganResponse> CreateThuNgan(@RequestBody @Valid ThuNganCreateRequest thungan) {
		
		try {
			return ApiResponse.<ThuNganResponse>builder().result(thuNgan.CreateThuNgan(thungan)).build();
		} catch (AppException ae) {
			return new ApiResponse<>(ae.getErrorCode().getCode(), ae.getErrorCode().getMessage(), null);
		} catch (Exception e) {
			return new ApiResponse<>(ErrorCode.UNKNOWN_ERROR.getCode(), ErrorCode.UNKNOWN_ERROR.getMessage(), null);
		}
	}
	
	@PostMapping("/updatethungan/{maThuNgan}")
	ApiResponse<ThuNganResponse> UpdateThuNgan(@PathVariable String maThuNgan, @RequestBody @Valid ThuNganUpdateRequest request)
	{
		try {
			return ApiResponse.<ThuNganResponse>builder().result(thuNgan.UpdateThuNgan(Integer.valueOf(maThuNgan), request)).build();
		}
		catch (AppException ae) {
			return new ApiResponse<>(ae.getErrorCode().getCode(), ae.getErrorCode().getMessage(), null);
		}
		catch (Exception e) {
			return new ApiResponse<>(ErrorCode.UNKNOWN_ERROR.getCode(), ErrorCode.UNKNOWN_ERROR.getMessage(), null);
		}

	}
}
