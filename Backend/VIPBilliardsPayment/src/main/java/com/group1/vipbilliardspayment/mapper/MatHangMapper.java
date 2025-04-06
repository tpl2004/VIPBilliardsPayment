package com.group1.vipbilliardspayment.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.group1.vipbilliardspayment.dto.request.MatHangCreateRequest;
import com.group1.vipbilliardspayment.dto.response.MatHangResponse;
import com.group1.vipbilliardspayment.entity.MatHang;

@Mapper(componentModel = "spring")
public interface MatHangMapper {

    @Mapping(target = "maHang", source = "maHang")
    @Mapping(target = "tenHang", source = "tenHang")
    @Mapping(target = "donGia", source = "donGia")
    MatHangResponse toMatHangResponse(MatHang matHang);
    
    @Mapping(target = "tenHang", source = "tenHang")
    @Mapping(target = "donGia", source = "donGia")
    MatHang toMatHang(MatHangCreateRequest request);
}
