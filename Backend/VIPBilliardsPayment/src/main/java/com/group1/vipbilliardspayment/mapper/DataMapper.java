package com.group1.vipbilliardspayment.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.group1.vipbilliardspayment.dto.response.BanBidaResponse;
import com.group1.vipbilliardspayment.dto.response.ThuNganResponse;
import com.group1.vipbilliardspayment.entity.BanBida;
import com.group1.vipbilliardspayment.entity.ThuNgan;

@Mapper(componentModel = "spring")
public interface DataMapper {
	@Mapping(source = "loaiBan.tenLoai", target =  "tenLoaiBan")
    @Mapping(source = "loaiBan.donGia", target = "donGia")  
    BanBidaResponse toBanBidaResponse(BanBida banBida);
	
	
	
	ThuNganResponse toThuNganReponse(ThuNgan thuNgan);
}
