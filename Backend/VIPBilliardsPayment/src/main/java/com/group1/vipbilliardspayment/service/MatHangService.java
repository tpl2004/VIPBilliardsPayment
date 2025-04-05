package com.group1.vipbilliardspayment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.group1.vipbilliardspayment.dto.request.MatHangCreateRequest;
import com.group1.vipbilliardspayment.dto.request.MatHangUpdateRequest;
import com.group1.vipbilliardspayment.dto.response.MatHangResponse;
import com.group1.vipbilliardspayment.entity.MatHang;
import com.group1.vipbilliardspayment.exception.AppException;
import com.group1.vipbilliardspayment.exception.ErrorCode;
import com.group1.vipbilliardspayment.mapper.MatHangMapper;
import com.group1.vipbilliardspayment.repository.MatHangRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MatHangService {

    MatHangRepository matHangRepository;
    MatHangMapper matHangMapper;
    
    public List<MatHangResponse> getAllMatHang() {
        return matHangRepository.findAll().stream().map(matHangMapper::toMatHangResponse).toList();
    }

    public MatHangResponse createMatHang(MatHangCreateRequest request) {
        if(matHangRepository.existsByTenHang(request.getTenHang())) {
            throw new AppException(ErrorCode.MATHANG_EXISTED);
        }
        MatHang matHang = matHangMapper.toMatHang(request);
        matHang = matHangRepository.save(matHang);
        return matHangMapper.toMatHangResponse(matHang);
    }
    
    public MatHangResponse updateMatHang(Integer id, MatHangUpdateRequest request) {
        MatHang matHang = matHangRepository.findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.MATHANG_NOTEXIST));
        if(matHang.getTenHang().equals(request.getTenHang())) {
            matHang.setDonGia(request.getDonGia());
        } else {
            matHangRepository.findByTenHang(request.getTenHang())
                .ifPresentOrElse(mh -> {
                    throw new AppException(ErrorCode.MATHANG_EXISTED);
                }, () -> {
                    matHang.setTenHang(request.getTenHang());
                    matHang.setDonGia(request.getDonGia());
                });
        }
        return matHangMapper.toMatHangResponse(matHangRepository.save(matHang));
    }
}
