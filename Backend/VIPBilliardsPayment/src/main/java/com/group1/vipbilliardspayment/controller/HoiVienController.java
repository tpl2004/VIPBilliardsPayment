package com.group1.vipbilliardspayment.controller;

import com.group1.vipbilliardspayment.dto.request.HoiVienCreateRequest;
import com.group1.vipbilliardspayment.dto.response.ApiResponse;
import com.group1.vipbilliardspayment.dto.response.HoiVienResponse;
import com.group1.vipbilliardspayment.entity.HoiVien;
import com.group1.vipbilliardspayment.service.HoiVienService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/hoivien")
@AllArgsConstructor
public class HoiVienController {
    private HoiVienService hoiVienService;

    @GetMapping("/all")
    public ApiResponse<List<HoiVienResponse>> getAllHoiVien() {
        return ApiResponse.<List<HoiVienResponse>>builder()
                .result(hoiVienService.getAllHoiVien())
                .build();
    }

    @PostMapping("/create")
    public ApiResponse<HoiVienResponse> createHoiVien(@RequestBody HoiVienCreateRequest request) {
        return ApiResponse.<HoiVienResponse>builder()
                .result(hoiVienService.CreateHoiVien(request))
                .build();
    }
}
