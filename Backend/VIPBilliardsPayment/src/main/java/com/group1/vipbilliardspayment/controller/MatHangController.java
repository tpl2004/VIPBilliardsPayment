package com.group1.vipbilliardspayment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group1.vipbilliardspayment.dto.request.MatHangCreateRequest;
import com.group1.vipbilliardspayment.dto.request.MatHangUpdateRequest;
import com.group1.vipbilliardspayment.dto.response.ApiResponse;
import com.group1.vipbilliardspayment.dto.response.MatHangResponse;
import com.group1.vipbilliardspayment.service.MatHangService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/mathang")
public class MatHangController {

    MatHangService matHangService;
    
    @GetMapping
    public ApiResponse<List<MatHangResponse>> getAllMatHang() {
        var result = matHangService.getAllMatHang();
        return ApiResponse.<List<MatHangResponse>>builder()
            .result(result)
            .build();
    }
    
    @PostMapping
    public ApiResponse<MatHangResponse> createMatHang(@Valid @RequestBody MatHangCreateRequest request) {
        var result = matHangService.createMatHang(request);
        return ApiResponse.<MatHangResponse>builder()
            .result(result)
            .build();
    }
    
    @PutMapping("/{id}")
    public ApiResponse<MatHangResponse> updateMatHang(@PathVariable(name = "id") Integer id, @Valid @RequestBody MatHangUpdateRequest request) {
        var result = matHangService.updateMatHang(id, request);
        return ApiResponse.<MatHangResponse>builder()
            .result(result)
            .build();
    }
}
