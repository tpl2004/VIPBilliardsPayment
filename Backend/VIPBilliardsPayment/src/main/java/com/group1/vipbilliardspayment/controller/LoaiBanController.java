package com.group1.vipbilliardspayment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group1.vipbilliardspayment.dto.request.LoaiBanCreateRequest;
import com.group1.vipbilliardspayment.dto.request.LoaiBanUpdateRequest;
import com.group1.vipbilliardspayment.dto.response.ApiResponse;
import com.group1.vipbilliardspayment.dto.response.LoaiBanResponse;
import com.group1.vipbilliardspayment.service.LoaiBanService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.var;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/loaiban")
public class LoaiBanController {

    LoaiBanService loaiBanService;
    
    @GetMapping
    public ApiResponse<List<LoaiBanResponse>> getAllLoaiBan() {
        var result = loaiBanService.getAllLoaiBan();
        return ApiResponse.<List<LoaiBanResponse>>builder()
            .result(result)
            .build();
    }
    
    @PostMapping
    public ApiResponse<LoaiBanResponse> createLoaiBan(@Valid @RequestBody LoaiBanCreateRequest request) {
        var result = loaiBanService.createLoaiBan(request);
        return ApiResponse.<LoaiBanResponse>builder()
            .result(result)
            .build();
    }
    
    @PutMapping("/{id}")
    public ApiResponse<LoaiBanResponse> updateLoaiBan(@PathVariable(name = "id") Integer id, @Valid @RequestBody LoaiBanUpdateRequest request) {
        var result = loaiBanService.updateLoaiBan(id, request);
        return ApiResponse.<LoaiBanResponse>builder()
            .result(result)
            .build();
    }
}
