package com.group1.vipbilliardspayment.service;

import com.group1.vipbilliardspayment.dto.request.MatHangTrongHoaDonUpdateRequest;
import com.group1.vipbilliardspayment.entity.HoaDon;
import com.group1.vipbilliardspayment.entity.MatHang;
import com.group1.vipbilliardspayment.entity.MatHangTrongHoaDon;
import com.group1.vipbilliardspayment.entity.MatHangTrongHoaDonId;
import com.group1.vipbilliardspayment.exception.AppException;
import com.group1.vipbilliardspayment.exception.ErrorCode;
import com.group1.vipbilliardspayment.repository.HoaDonRepository;
import com.group1.vipbilliardspayment.repository.MatHangRepository;
import com.group1.vipbilliardspayment.dto.response.MatHangTrongHoaDonResponse;
import com.group1.vipbilliardspayment.mapper.MatHangTrongHoaDonMapper;
import com.group1.vipbilliardspayment.repository.MatHangTrongHoaDonRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MatHangTrongHoaDonService {
    MatHangTrongHoaDonRepository matHangTrongHoaDonRepository;

    MatHangRepository matHangRepository;

    HoaDonRepository hoaDonRepository;

    MatHangTrongHoaDonMapper matHangTrongHoaDonMapper;

    public MatHangTrongHoaDonResponse updateMatHangTrongHoaDon(MatHangTrongHoaDonUpdateRequest request) {
        MatHang matHang = matHangRepository.findById(request.getMaHang()).orElseThrow(() -> new AppException(ErrorCode.MATHANGTRONGHOADON_NOT_EXISTED));

        HoaDon hoaDon = hoaDonRepository.findById(request.getMaHoaDon()).orElseThrow(() -> new AppException(ErrorCode.MATHANGTRONGHOADON_NOT_EXISTED));

        MatHangTrongHoaDonId matHangTrongHoaDonId = new MatHangTrongHoaDonId(matHang, hoaDon);

        MatHangTrongHoaDon matHangTrongHoaDon = matHangTrongHoaDonRepository.findById(matHangTrongHoaDonId).orElseThrow(() -> new AppException(ErrorCode.MATHANGTRONGHOADON_NOT_EXISTED));

        matHangTrongHoaDon.setSoLuong(request.getSoLuong());

        return matHangTrongHoaDonMapper.toMatHangTrongHoaDonResponse(matHangTrongHoaDonRepository.save(matHangTrongHoaDon));
    }

    public List<MatHangTrongHoaDonResponse> getMatHangTrongHoaDon(Integer maHoaDon) {
        HoaDon hoaDon = hoaDonRepository.findById(maHoaDon)
                .orElseThrow(() -> new AppException(ErrorCode.HOADON_NOT_EXIST));

        List<MatHangTrongHoaDon> matHangTrongHoaDonList = matHangTrongHoaDonRepository.findByHoaDon(hoaDon);

        List<MatHangTrongHoaDonResponse> matHangTrongHoaDonResponseList = new ArrayList<>();

        for(MatHangTrongHoaDon i : matHangTrongHoaDonList) {
            matHangTrongHoaDonResponseList.add(matHangTrongHoaDonMapper.toMatHangTrongHoaDonResponse(i));
        }

        return matHangTrongHoaDonResponseList;
    }
}
