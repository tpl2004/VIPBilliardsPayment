package com.group1.vipbilliardspayment.service;

import com.group1.vipbilliardspayment.dto.request.HoiVienCreateRequest;
import com.group1.vipbilliardspayment.dto.request.HoiVienUpdateRequest;
import com.group1.vipbilliardspayment.dto.response.HoiVienResponse;
import com.group1.vipbilliardspayment.entity.CapDoHoiVien;
import com.group1.vipbilliardspayment.entity.HoiVien;
import com.group1.vipbilliardspayment.exception.AppException;
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

    public HoiVienResponse createHoiVien(HoiVienCreateRequest request) {
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

        HoiVien hoiVien = hoiVienMapper.toHoiVien(request);
        hoiVien.setSoGioChoi(0.0);
        hoiVien.setNgayDangKy(new Date());
        hoiVien.setCapDo(danhSachCapDoHoiVien.get(minOfSoGioChoiIndex));

        return hoiVienMapper.toHoiVienResponse(hoiVienRepository.save(hoiVien));
    }

    public HoiVienResponse updateHoiVien(Integer maHoiVien ,HoiVienUpdateRequest request) {
        HoiVien hoiVien = hoiVienRepository.findById(maHoiVien).orElseThrow(() -> new AppException(ErrorCode.HOIVIEN_NOT_EXISTED));

        if(hoiVienRepository.existsBySoCCCD(request.getSoCCCD())) {
            throw new AppException(ErrorCode.SOCCCD_EXISTED);
        }

        hoiVien.setHoTen(request.getHoTen());
        hoiVien.setEmail(request.getEmail());
        hoiVien.setSoDienThoai(request.getSoDienThoai());
        hoiVien.setSoCCCD(request.getSoCCCD());

        return hoiVienMapper.toHoiVienResponse(hoiVienRepository.save(hoiVien));
    }
}
