package com.group1.vipbilliardspayment.service;

import com.group1.vipbilliardspayment.dto.request.CapDoHoiVienCreateRequest;
import com.group1.vipbilliardspayment.dto.request.CapDoHoiVienUpdateRequest;
import com.group1.vipbilliardspayment.dto.response.CapDoHoiVienResponse;
import com.group1.vipbilliardspayment.dto.response.HoiVienResponse;
import com.group1.vipbilliardspayment.entity.CapDoHoiVien;
import com.group1.vipbilliardspayment.exception.AppException;
import com.group1.vipbilliardspayment.exception.ErrorCode;
import com.group1.vipbilliardspayment.mapper.CapDoHoiVienMapper;
import com.group1.vipbilliardspayment.repository.CapDoHoiVienRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CapDoHoiVienService {
    CapDoHoiVienRepository capDoHoiVienRepository;

    CapDoHoiVienMapper capDoHoiVienMapper;

    public List<CapDoHoiVienResponse> getAllCapDoHoiVien() {
        List<CapDoHoiVien> danhSachCapDoHoiVien = capDoHoiVienRepository.findAll();

        List<CapDoHoiVienResponse> danhSachCapDoHoiVienResponse = new ArrayList<>();

        for(CapDoHoiVien i : danhSachCapDoHoiVien) {
            CapDoHoiVienResponse capDoHoiVienResponse = capDoHoiVienMapper.toCapDoHoiVienResponse(i);

            danhSachCapDoHoiVienResponse.add(capDoHoiVienResponse);
        }

        return danhSachCapDoHoiVienResponse;
    }

    public CapDoHoiVienResponse createCapDoHoiVien(CapDoHoiVienCreateRequest capDoHoiVienCreateRequest) {
        if(capDoHoiVienRepository.existsByTenCapDo(capDoHoiVienCreateRequest.getTenCapDo())) {
            throw new AppException(ErrorCode.TENCAPDO_EXISTED);
        }

        if(capDoHoiVienRepository.existsBySoGioChoi(capDoHoiVienCreateRequest.getSoGioChoi())) {
            throw new AppException(ErrorCode.SOGIOCHOI_EXISTED);
        }

        CapDoHoiVien capDoHoiVien = CapDoHoiVien.builder()
                .tenCapDo(capDoHoiVienCreateRequest.getTenCapDo())
                .soGioChoi(capDoHoiVienCreateRequest.getSoGioChoi())
                .uuDai(capDoHoiVienCreateRequest.getUuDai())
                .build();

        return capDoHoiVienMapper.toCapDoHoiVienResponse(capDoHoiVienRepository.save(capDoHoiVien));
    }

    public CapDoHoiVienResponse updateCapDoHoiVien(Integer id, CapDoHoiVienUpdateRequest capDoHoiVienUpdateRequest) {
        CapDoHoiVien capDoHoiVien = capDoHoiVienRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CAPDOHOIVIEN_NOTFOUND));

        if(!capDoHoiVien.getTenCapDo().equals(capDoHoiVienUpdateRequest.getTenCapDo()) && capDoHoiVienRepository.existsByTenCapDo(capDoHoiVienUpdateRequest.getTenCapDo())) {
            throw new AppException(ErrorCode.TENCAPDO_EXISTED);
        }

        if(capDoHoiVien.getSoGioChoi() != capDoHoiVienUpdateRequest.getSoGioChoi() && capDoHoiVienRepository.existsBySoGioChoi(capDoHoiVienUpdateRequest.getSoGioChoi())) {
            throw new AppException(ErrorCode.SOGIOCHOI_EXISTED);
        }

        capDoHoiVien.setTenCapDo(capDoHoiVienUpdateRequest.getTenCapDo());
        capDoHoiVien.setSoGioChoi(capDoHoiVienUpdateRequest.getSoGioChoi());
        capDoHoiVien.setUuDai(capDoHoiVienUpdateRequest.getUuDai());

        return capDoHoiVienMapper.toCapDoHoiVienResponse(capDoHoiVienRepository.save(capDoHoiVien));
    }

}
