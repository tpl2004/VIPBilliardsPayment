package com.group1.vipbilliardspayment.controller;

import com.group1.vipbilliardspayment.dto.request.CapDoHoiVienCreateRequest;
import com.group1.vipbilliardspayment.dto.response.ApiResponse;
import com.group1.vipbilliardspayment.dto.response.CapDoHoiVienResponse;
import com.group1.vipbilliardspayment.entity.CapDoHoiVien;
import com.group1.vipbilliardspayment.service.CapDoHoiVienService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/capdohoivien")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CapDoHoiVienController {
    CapDoHoiVienService capDoHoiVienService;

    @GetMapping
    public ApiResponse<List<CapDoHoiVienResponse>> getAllCapDoHoiVien() {
        return ApiResponse.<List<CapDoHoiVienResponse>>builder()
                .result(capDoHoiVienService.getAllCapDoHoiVien())
                .build();
    }

    @PostMapping
    public ApiResponse<CapDoHoiVienResponse> createCapDoHoiVien(@RequestBody @Valid CapDoHoiVienCreateRequest capDoHoiVienCreateRequest) {
        return ApiResponse.<CapDoHoiVienResponse>builder()
                .result(capDoHoiVienService.createCapDoHoiVien(capDoHoiVienCreateRequest))
                .build();
    }
}
