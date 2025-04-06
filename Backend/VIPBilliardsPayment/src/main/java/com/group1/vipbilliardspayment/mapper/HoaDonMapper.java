package com.group1.vipbilliardspayment.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.group1.vipbilliardspayment.dto.request.HoaDonCreateRequest;
import com.group1.vipbilliardspayment.dto.response.HoaDonResponse;
import com.group1.vipbilliardspayment.entity.HoaDon;

@Mapper(componentModel = "spring")
public interface HoaDonMapper {

    @Mapping(target = "maHoaDon", source = "maHoaDon")
    @Mapping(target = "thoiDiemVao", source = "thoiDiemVao")
    @Mapping(target = "thoiDiemRa", source = "thoiDiemRa")
    @Mapping(target = "soGioChoi", source = "soGioChoi")
    @Mapping(target = "soBan", source = "banBida.soBan")
    @Mapping(target = "tenLoaiBan", source = "banBida.loaiBan.tenLoai")
    @Mapping(target = "donGia", source = "banBida.loaiBan.donGia")
    @Mapping(target = "tenThuNgan", source = "thuNgan.hoTen")
    @Mapping(target = "tenHoiVien", source = "hoiVien.hoTen")
    @Mapping(target = "tenCapDo", source = "hoiVien.capDo.tenCapDo")
    @Mapping(target = "uuDai", source = "hoiVien.capDo.uuDai")
    @Mapping(target = "tongTien", source = "tongTien")
    HoaDonResponse toHoaDonResponse(HoaDon hoaDon);
    
}
