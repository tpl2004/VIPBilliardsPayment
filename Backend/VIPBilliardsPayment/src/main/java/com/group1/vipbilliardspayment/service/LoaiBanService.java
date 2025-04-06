package com.group1.vipbilliardspayment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.group1.vipbilliardspayment.dto.request.LoaiBanCreateRequest;
import com.group1.vipbilliardspayment.dto.request.LoaiBanUpdateRequest;
import com.group1.vipbilliardspayment.dto.response.LoaiBanResponse;
import com.group1.vipbilliardspayment.entity.LoaiBan;
import com.group1.vipbilliardspayment.exception.AppException;
import com.group1.vipbilliardspayment.exception.ErrorCode;
import com.group1.vipbilliardspayment.mapper.LoaiBanMapper;
import com.group1.vipbilliardspayment.repository.LoaiBanRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoaiBanService {

    LoaiBanRepository loaiBanRepository;
    LoaiBanMapper loaiBanMapper;
    
    public List<LoaiBanResponse> getAllLoaiBan() {
        return loaiBanRepository.findAll().stream().map(loaiBanMapper::toLoaiBanResponse).toList();
    }
    
    public LoaiBanResponse createLoaiBan(LoaiBanCreateRequest request) {
        if(loaiBanRepository.existsByTenLoai(request.getTenLoai())) {
            throw new AppException(ErrorCode.LOAIBAN_EXISTED);
        }
        LoaiBan loaiBan = loaiBanMapper.toLoaiBan(request);
        loaiBan = loaiBanRepository.save(loaiBan);
        return loaiBanMapper.toLoaiBanResponse(loaiBan);
    }
    
    public LoaiBanResponse updateLoaiBan(Integer id, LoaiBanUpdateRequest request) {
        LoaiBan loaiBan = loaiBanRepository.findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.LOAIBAN_NOTEXIST));
        if(loaiBan.getTenLoai().equals(request.getTenLoai())) {
            loaiBan.setDonGia(request.getDonGia());
        } else {
            if(loaiBanRepository.findByTenLoai(request.getTenLoai()).isPresent()) {
                throw new AppException(ErrorCode.TENLOAIBAN_EXISTED);
            }
            loaiBan.setTenLoai(request.getTenLoai());
            loaiBan.setDonGia(request.getDonGia());
        }
        return loaiBanMapper.toLoaiBanResponse(loaiBanRepository.save(loaiBan));
    }
}
