package com.group1.vipbilliardspayment.mapper;

import com.group1.vipbilliardspayment.dto.response.MatHangTrongHoaDonResponse;
import com.group1.vipbilliardspayment.entity.HoaDon;
import com.group1.vipbilliardspayment.entity.MatHang;
import com.group1.vipbilliardspayment.entity.MatHangTrongHoaDon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface MatHangTrongHoaDonMapper {
    @Mapping(source = "matHang", target = "maHang", qualifiedByName = "matHangToMaHang")
    @Mapping(source = "matHang", target = "tenHang", qualifiedByName = "matHangToTenHang")
    @Mapping(source = "hoaDon", target = "maHoaDon", qualifiedByName = "hoaDonToMaHoaDon")
    @Mapping(source = "matHang", target = "donGia", qualifiedByName = "matHangToDonGia")
    MatHangTrongHoaDonResponse toMatHangTrongHoaDonResponse(MatHangTrongHoaDon matHangTrongHoaDon);

    @Named("matHangToMaHang")
    default Integer matHangToMaHang(MatHang matHang) {
        return matHang.getMaHang();
    }

    @Named("matHangToTenHang")
    default String matHangToTenHang(MatHang matHang) {
        return matHang.getTenHang();
    }

    @Named("hoaDonToMaHoaDon")
    default Integer hoaDonToMaHoaDon(HoaDon hoaDon) {
        return hoaDon.getMaHoaDon();
    }

    @Named("matHangToDonGia")
    default Double matHangToDonGia(MatHang matHang) {
        return matHang.getDonGia();
    }
}
