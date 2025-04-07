package com.group1.vipbilliardspayment.service;

import com.group1.vipbilliardspayment.repository.MatHangTrongHoaDonRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MatHangTrongHoaDonService {
    MatHangTrongHoaDonRepository matHangTrongHoaDonRepository;
}
