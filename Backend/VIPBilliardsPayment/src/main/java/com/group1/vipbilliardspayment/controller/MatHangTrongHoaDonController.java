package com.group1.vipbilliardspayment.controller;

import com.group1.vipbilliardspayment.dto.request.MatHangTrongHoaDonCreateRequest;
import com.group1.vipbilliardspayment.dto.response.ApiResponse;
import com.group1.vipbilliardspayment.dto.response.MatHangTrongHoaDonResponse;
import com.group1.vipbilliardspayment.service.MatHangTrongHoaDonService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mathangtronghoadon")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MatHangTrongHoaDonController {
    MatHangTrongHoaDonService matHangTrongHoaDonService;

    @GetMapping("/{id}")
    public ApiResponse<List<MatHangTrongHoaDonResponse>> getMatHangTrongHoaDon(@PathVariable(value = "id") Integer maHoaDon) {
        return ApiResponse.<List<MatHangTrongHoaDonResponse>>builder()
                .result(matHangTrongHoaDonService.getMatHangTrongHoaDon(maHoaDon))
                .build();
    }

    @PostMapping
    public ApiResponse<MatHangTrongHoaDonResponse> createMatHangTrongHoaDon(@RequestBody MatHangTrongHoaDonCreateRequest matHangTrongHoaDonCreateRequest) {
        return ApiResponse.<MatHangTrongHoaDonResponse>builder()
                .result(matHangTrongHoaDonService.createMatHangTrongHoaDon(matHangTrongHoaDonCreateRequest))
                .build();
    }
}
