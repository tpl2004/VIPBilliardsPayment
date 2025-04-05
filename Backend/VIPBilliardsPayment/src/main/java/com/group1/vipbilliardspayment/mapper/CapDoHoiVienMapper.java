package com.group1.vipbilliardspayment.mapper;

import com.group1.vipbilliardspayment.dto.response.CapDoHoiVienResponse;
import com.group1.vipbilliardspayment.entity.CapDoHoiVien;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CapDoHoiVienMapper {
    CapDoHoiVienResponse toCapDoHoiVienResponse(CapDoHoiVien capDoHoiVien);
}
