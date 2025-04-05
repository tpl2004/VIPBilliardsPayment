package com.group1.vipbilliardspayment.mapper;

import com.group1.vipbilliardspayment.dto.response.HoiVienResponse;
import com.group1.vipbilliardspayment.entity.CapDoHoiVien;
import com.group1.vipbilliardspayment.entity.HoiVien;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HoiVienMapper {
    @Mapping(source = "capDo", target = "tenCapDo", qualifiedByName = "mapTenCapDo")
    HoiVienResponse toHoiVienResponse(HoiVien hoiVien);

    default String mapTenCapDo(CapDoHoiVien capDo) {
        return capDo.getTenCapDo();
    }
}
