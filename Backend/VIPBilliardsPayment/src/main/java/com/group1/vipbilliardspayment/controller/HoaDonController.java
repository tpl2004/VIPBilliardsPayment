package com.group1.vipbilliardspayment.controller;

import java.util.Date;
import java.util.List;

import com.group1.vipbilliardspayment.dto.request.ThongKeDoanhThuTheoNgayRequest;
import com.group1.vipbilliardspayment.dto.response.ThongKeDoanhThuTheoNgayResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group1.vipbilliardspayment.dto.request.HoaDonCreateRequest;
import com.group1.vipbilliardspayment.dto.request.HoaDonUpdateRequest;
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
    
    @PutMapping("/{id}")
    public ApiResponse<HoaDonResponse> updateHoaDon(@PathVariable(name = "id") Integer id, @RequestBody HoaDonUpdateRequest request) {
        var result = hoaDonService.updateHoaDon(id, request);
        return ApiResponse.<HoaDonResponse>builder()
            .result(result)
            .build();
    }

    @GetMapping("/findbydate")
    public ApiResponse<List<HoaDonResponse>> findHoaDonByDate(@RequestBody Date createdDate) {
        return ApiResponse.<List<HoaDonResponse>>builder()
                .result(hoaDonService.findHoaDonByDate(createdDate))
                .build();
    }

    @PutMapping("/thanhtoan/{id}")
    public ApiResponse<HoaDonResponse> thanhToanHoaDon(@PathVariable(value = "id") Integer soBan) {
        return ApiResponse.<HoaDonResponse>builder()
                .result(hoaDonService.thanhToanHoaDon(soBan))
                .build();
    }

    @PostMapping("/thongke")
    public ApiResponse<List<ThongKeDoanhThuTheoNgayResponse>> thongKeDoanhThuTheoNgay(@RequestBody @Valid ThongKeDoanhThuTheoNgayRequest thongKeDoanhThuTheoNgayRequest) {
        return ApiResponse.<List<ThongKeDoanhThuTheoNgayResponse>>builder()
                .result(hoaDonService.thongKeDoanhThuTheoNgay(thongKeDoanhThuTheoNgayRequest))
                .build();
    }
}
