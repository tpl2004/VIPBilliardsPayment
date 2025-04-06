package com.group1.vipbilliardspayment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group1.vipbilliardspayment.dto.request.HoaDonCreateRequest;
import com.group1.vipbilliardspayment.dto.response.ApiResponse;
import com.group1.vipbilliardspayment.dto.response.HoaDonResponse;
import com.group1.vipbilliardspayment.service.HoaDonService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/hoadon")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HoaDonController {

    HoaDonService hoaDonService;
    
    @GetMapping
    public ApiResponse<List<HoaDonResponse>> getAllHoaDon() {
        var result = hoaDonService.getAllHoaDon();
        return ApiResponse.<List<HoaDonResponse>>builder()
            .result(result)
            .build();
    }
    
    @PostMapping
    public ApiResponse<HoaDonResponse> createHoaDon(@Valid @RequestBody HoaDonCreateRequest request) {
        var result = hoaDonService.createHoaDon(request);
        return ApiResponse.<HoaDonResponse>builder()
            .result(result)
            .build();
    }
}
