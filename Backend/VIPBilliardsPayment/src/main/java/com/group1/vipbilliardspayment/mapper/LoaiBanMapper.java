package com.group1.vipbilliardspayment.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.group1.vipbilliardspayment.dto.request.LoaiBanCreateRequest;
import com.group1.vipbilliardspayment.dto.response.LoaiBanResponse;
import com.group1.vipbilliardspayment.entity.LoaiBan;

@Mapper(componentModel = "spring")
public interface LoaiBanMapper {

    @Mapping(target = "loaiBan", source = "loaiBan")
    @Mapping(target = "tenLoai", source = "tenLoai")
    @Mapping(target = "donGia", source = "donGia")
    LoaiBanResponse toLoaiBanResponse(LoaiBan loaiBan);
    
    @Mapping(target = "tenLoai", source = "tenLoai")
    @Mapping(target = "donGia", source = "donGia")
    LoaiBan toLoaiBan(LoaiBanCreateRequest request);
}
