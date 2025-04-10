package com.group1.vipbilliardspayment.controller;

import com.group1.vipbilliardspayment.dto.request.MatHangTrongHoaDonUpdateRequest;
import com.group1.vipbilliardspayment.dto.request.MatHangTrongHoaDonCreateRequest;
import com.group1.vipbilliardspayment.dto.response.ApiResponse;
import com.group1.vipbilliardspayment.dto.response.MatHangTrongHoaDonResponse;
import com.group1.vipbilliardspayment.service.MatHangTrongHoaDonService;
import jakarta.validation.Valid;
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

    @PutMapping
    public ApiResponse<MatHangTrongHoaDonResponse> updateMatHangTrongHoaDon(@RequestBody @Valid MatHangTrongHoaDonUpdateRequest request) {
        return ApiResponse.<MatHangTrongHoaDonResponse>builder()
                .result(matHangTrongHoaDonService.updateMatHangTrongHoaDon(request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<List<MatHangTrongHoaDonResponse>> getMatHangTrongHoaDon(@PathVariable(value = "id") Integer maHoaDon) {
        return ApiResponse.<List<MatHangTrongHoaDonResponse>>builder()
                .result(matHangTrongHoaDonService.getMatHangTrongHoaDon(maHoaDon))
                .build();
    }

    @PostMapping
    public ApiResponse<MatHangTrongHoaDonResponse> createMatHangTrongHoaDon(@RequestBody @Valid MatHangTrongHoaDonCreateRequest matHangTrongHoaDonCreateRequest) {
        return ApiResponse.<MatHangTrongHoaDonResponse>builder()
                .result(matHangTrongHoaDonService.createMatHangTrongHoaDon(matHangTrongHoaDonCreateRequest))
                .build();
    }

    @DeleteMapping("/{mahoadon}/{mahang}")
    public ApiResponse<String> deleteMatHangTrongHoaDon(@PathVariable(value = "mahang") Integer maHang, @PathVariable(value = "mahoadon") Integer maHoaDon) {
        return ApiResponse.<String>builder()
                .result(matHangTrongHoaDonService.deleteMatHangTrongHoaDon(maHang, maHoaDon))
                .build();
    }
}
