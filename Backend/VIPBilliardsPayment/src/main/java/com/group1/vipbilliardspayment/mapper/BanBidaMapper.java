package com.group1.vipbilliardspayment.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.group1.vipbilliardspayment.dto.response.BanBidaResponse;
import com.group1.vipbilliardspayment.entity.BanBida;

@Mapper(componentModel = "spring")
public interface BanBidaMapper {

	@Mapping(source = "loaiBan.tenLoai", target =  "tenLoaiBan")
    @Mapping(source = "loaiBan.donGia", target = "donGia")  
    BanBidaResponse toBanBidaResponse(BanBida banBida);
}
