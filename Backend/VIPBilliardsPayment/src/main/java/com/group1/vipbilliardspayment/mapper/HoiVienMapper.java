package com.group1.vipbilliardspayment.mapper;

import com.group1.vipbilliardspayment.dto.request.HoiVienCreateRequest;
import com.group1.vipbilliardspayment.dto.request.HoiVienUpdateRequest;
import com.group1.vipbilliardspayment.dto.response.HoiVienResponse;
import com.group1.vipbilliardspayment.entity.CapDoHoiVien;
import com.group1.vipbilliardspayment.entity.HoiVien;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface HoiVienMapper {
    @Mapping(source = "capDo", target = "tenCapDo", qualifiedByName = "mapTenCapDo")
    HoiVienResponse toHoiVienResponse(HoiVien hoiVien);

    @Named("mapTenCapDo")
    default String mapTenCapDo(CapDoHoiVien capDo) {
        return capDo.getTenCapDo();
    }

    HoiVien toHoiVien(HoiVienCreateRequest hoiVienCreateRequest);

    HoiVien toHoiVien(HoiVienUpdateRequest hoiVienUpdateRequest);
}
