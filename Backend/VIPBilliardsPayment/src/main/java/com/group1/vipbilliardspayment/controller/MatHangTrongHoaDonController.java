package com.group1.vipbilliardspayment.controller;

import com.group1.vipbilliardspayment.dto.request.MatHangTrongHoaDonUpdateRequest;
import com.group1.vipbilliardspayment.dto.response.ApiResponse;
import com.group1.vipbilliardspayment.dto.response.MatHangTrongHoaDonResponse;
import com.group1.vipbilliardspayment.service.MatHangTrongHoaDonService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
