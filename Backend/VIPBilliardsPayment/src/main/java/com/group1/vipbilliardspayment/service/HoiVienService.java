package com.group1.vipbilliardspayment.service;

import com.group1.vipbilliardspayment.dto.request.HoiVienCreateRequest;
import com.group1.vipbilliardspayment.dto.response.HoiVienResponse;
import com.group1.vipbilliardspayment.entity.CapDoHoiVien;
import com.group1.vipbilliardspayment.entity.HoiVien;
import com.group1.vipbilliardspayment.exception.ErrorCode;
import com.group1.vipbilliardspayment.mapper.HoiVienMapper;
import com.group1.vipbilliardspayment.repository.CapDoHoiVienRepository;
import com.group1.vipbilliardspayment.repository.HoiVienRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class HoiVienService {
    private HoiVienRepository hoiVienRepository;

    private CapDoHoiVienRepository capDoHoiVienRepository;

    private HoiVienMapper hoiVienMapper;

    public List<HoiVienResponse> getAllHoiVien() {
        List<HoiVienResponse> danhSachHoiVienResponse = new ArrayList<>();

        List<HoiVien> danhSachHoiVien = hoiVienRepository.findAll();
        for(HoiVien i : danhSachHoiVien) {
            HoiVienResponse  hoiVienResponse = HoiVienResponse.builder()
                    .maHoiVien(i.getMaHoiVien())
                    .hoTen(i.getHoTen())
                    .email(i.getEmail())
                    .soDienThoai(i.getSoDienThoai())
                    .soCCCD(i.getSoCCCD())
                    .ngayDangKy(i.getNgayDangKy())
                    .tenCapDo(i.getCapDo().getTenCapDo())
                    .build();
            danhSachHoiVienResponse.add(hoiVienResponse);
        }

        return danhSachHoiVienResponse;
    }

    public HoiVienResponse CreateHoiVien(HoiVienCreateRequest request) {
        List<CapDoHoiVien> danhSachCapDoHoiVien = capDoHoiVienRepository.findAll();

        if(danhSachCapDoHoiVien.size() == 0) {
            throw new AppException(ErrorCode.CAPDOHOIVIENLIST_EMPTY);
        }

        if(hoiVienRepository.existsBySoCCCD(request.getSoCCCD())) {
            throw new AppException(ErrorCode.SOCCCD_EXISTED);
        }

        int minOfSoGioChoiIndex = 0;

        for(int i = 1; i < danhSachCapDoHoiVien.size(); i++) {
            if (danhSachCapDoHoiVien.get(i).getSoGioChoi() < danhSachCapDoHoiVien.get(minOfSoGioChoiIndex).getSoGioChoi()) {
                minOfSoGioChoiIndex = i;
            }
        }

        HoiVien hoiVien = HoiVien.builder()
                .hoTen(request.getHoTen())
                .email(request.getEmail())
                .soDienThoai(request.getSoDienThoai())
                .soCCCD(request.getSoCCCD())
                .capDo(danhSachCapDoHoiVien.get(minOfSoGioChoiIndex))
                .build();

        hoiVienRepository.save(hoiVien);

        return hoiVienMapper.toHoiVienResponse(hoiVien);
    }
}
