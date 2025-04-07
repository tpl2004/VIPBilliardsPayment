package com.group1.vipbilliardspayment.controller;

import com.group1.vipbilliardspayment.dto.request.HoiVienCreateRequest;
import com.group1.vipbilliardspayment.dto.request.HoiVienUpdateRequest;
import com.group1.vipbilliardspayment.dto.response.ApiResponse;
import com.group1.vipbilliardspayment.dto.response.HoiVienResponse;
import com.group1.vipbilliardspayment.entity.HoiVien;
import com.group1.vipbilliardspayment.service.HoiVienService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hoivien")
@AllArgsConstructor
public class HoiVienController {
    private HoiVienService hoiVienService;

    @GetMapping
    public ApiResponse<List<HoiVienResponse>> getAllHoiVien() {
        return ApiResponse.<List<HoiVienResponse>>builder()
                .result(hoiVienService.getAllHoiVien())
                .build();
    }

    @PostMapping
    public ApiResponse<HoiVienResponse> createHoiVien(@RequestBody @Valid HoiVienCreateRequest request) {
        return ApiResponse.<HoiVienResponse>builder()
                .result(hoiVienService.createHoiVien(request))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<HoiVienResponse> updateHoiVien(@PathVariable(value = "id") Integer maHoiVien, @RequestBody @Valid HoiVienUpdateRequest request) {
        return ApiResponse.<HoiVienResponse>builder()
                .result(hoiVienService.updateHoiVien(maHoiVien, request))
                .build();
    }
}
