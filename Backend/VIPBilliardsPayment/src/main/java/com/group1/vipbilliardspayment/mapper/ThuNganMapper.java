package com.group1.vipbilliardspayment.mapper;

import org.mapstruct.Mapper;

import com.group1.vipbilliardspayment.dto.response.ThuNganResponse;
import com.group1.vipbilliardspayment.entity.ThuNgan;

@Mapper(componentModel = "spring")
public interface ThuNganMapper {

	ThuNganResponse toThuNganReponse(ThuNgan thuNgan);
}
