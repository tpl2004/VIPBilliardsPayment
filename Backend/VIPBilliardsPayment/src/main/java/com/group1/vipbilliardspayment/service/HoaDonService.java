package com.group1.vipbilliardspayment.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.group1.vipbilliardspayment.dto.response.MatHangTrongHoaDonResponse;
import org.springframework.stereotype.Service;

import com.group1.vipbilliardspayment.dto.request.HoaDonCreateRequest;
import com.group1.vipbilliardspayment.dto.request.HoaDonUpdateRequest;
import com.group1.vipbilliardspayment.dto.response.HoaDonResponse;
import com.group1.vipbilliardspayment.entity.BanBida;
import com.group1.vipbilliardspayment.entity.HoaDon;
import com.group1.vipbilliardspayment.entity.HoiVien;
import com.group1.vipbilliardspayment.entity.ThuNgan;
import com.group1.vipbilliardspayment.exception.AppException;
import com.group1.vipbilliardspayment.exception.ErrorCode;
import com.group1.vipbilliardspayment.mapper.HoaDonMapper;
import com.group1.vipbilliardspayment.repository.BanBidaRepository;
import com.group1.vipbilliardspayment.repository.HoaDonRepository;
import com.group1.vipbilliardspayment.repository.HoiVienRepository;
import com.group1.vipbilliardspayment.repository.ThuNganRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HoaDonService {

    HoaDonRepository hoaDonRepository;
    HoaDonMapper hoaDonMapper;
    BanBidaRepository banBidaRepository;
    ThuNganRepository thuNganRepository;
    HoiVienRepository hoiVienRepository;
    MatHangTrongHoaDonService matHangTrongHoaDonService;
    
    public List<HoaDonResponse> getAllHoaDon() {
        return hoaDonRepository.findAll().stream().map(hoaDonMapper::toHoaDonResponse).toList();
    }
    
    public HoaDonResponse createHoaDon(HoaDonCreateRequest request) {
        BanBida banBida = banBidaRepository.findById(request.getSoBan())
            .orElseThrow(() -> new AppException(ErrorCode.BANBIDA_NOTEXIST));
        ThuNgan thuNgan = thuNganRepository.findById(request.getMaThuNgan())
            .orElseThrow(() -> new AppException(ErrorCode.THUNGAN_NOT_FOUND));
        if(banBida.getTrangThai().equals(0)) {
            HoaDon newHoaDon = HoaDon.builder()
                .banBida(banBida)
                .thuNgan(thuNgan)
                .thoiDiemVao(new Date())
                .thoiDiemRa(null)
                .soGioChoi(null)
                .tongTien(null)
                .trangThai(false)
                .hoiVien(null)
                .build();
            newHoaDon = hoaDonRepository.save(newHoaDon);
            return hoaDonMapper.toHoaDonResponse(newHoaDon);
        } else {
            throw new AppException(ErrorCode.BANBIDA_NOT_AVAILABLE);
        }
    }
    
    public HoaDonResponse updateHoaDon(Integer id, HoaDonUpdateRequest request) {
        HoaDon hoaDon = hoaDonRepository.findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.HOADON_NOT_EXIST));
        if(Objects.isNull(request.getMaHoiVien())) {
            hoaDon.setHoiVien(null);
        } else {
            HoiVien hoiVien = hoiVienRepository.findById(request.getMaHoiVien())
                .orElseThrow(() -> new AppException(ErrorCode.HOIVIEN_NOT_EXISTED));
            hoaDon.setHoiVien(hoiVien);
        }
        hoaDon = hoaDonRepository.save(hoaDon);
        return hoaDonMapper.toHoaDonResponse(hoaDon);
    }

    public List<HoaDonResponse> findHoaDonByDate(Date createdDate) {
        List<HoaDon> hoaDonList = hoaDonRepository.findByThoiDiemVao(createdDate);

        List<HoaDonResponse> hoaDonResponseList = new ArrayList<>();

        for(HoaDon i : hoaDonList) {
            hoaDonResponseList.add(hoaDonMapper.toHoaDonResponse(i));
        }

        return hoaDonResponseList;
    }

    public HoaDonResponse thanhToanHoaDon(Integer soBan) {
        BanBida banBida = banBidaRepository.findById(soBan).orElseThrow(() -> new AppException(ErrorCode.BANBIDA_NOTEXIST));

        if(banBida.getTrangThai() == 0 || banBida.getTrangThai() == 2) {
            throw new AppException(ErrorCode.HOADON_CANNOTTPAY);
        }

        List<HoaDon> hoaDonList = hoaDonRepository.findByBanBida(banBida);

        HoaDon hoaDon = null;

        for(HoaDon i : hoaDonList) {
            if(!i.isTrangThai()) {
                hoaDon = i;
                break;
            }
        }

        double soGioChoi = (hoaDon.getThoiDiemRa().getTime() - hoaDon.getThoiDiemVao().getTime()) / (double) 60000;

        List<MatHangTrongHoaDonResponse> matHangTrongHoaDonResponseList = matHangTrongHoaDonService.getMatHangTrongHoaDon(hoaDon.getMaHoaDon());

        double tongTienMatHang = 0;

        for(MatHangTrongHoaDonResponse i : matHangTrongHoaDonResponseList) {
            tongTienMatHang += i.getDonGia() * i.getSoLuong();
        }

        double tongTien = soGioChoi * hoaDon.getBanBida().getLoaiBan().getDonGia() + tongTienMatHang;

        if(hoaDon.getHoiVien() != null) {
            tongTien -= tongTien * hoaDon.getHoiVien().getCapDo().getUuDai();
        }

        hoaDon.setSoGioChoi(soGioChoi);
        hoaDon.setTongTien(tongTien);
        hoaDon.setTrangThai(true);

        return hoaDonMapper.toHoaDonResponse(hoaDonRepository.save(hoaDon));
    }
}
