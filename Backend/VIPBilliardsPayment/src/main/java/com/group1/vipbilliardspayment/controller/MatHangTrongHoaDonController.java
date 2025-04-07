package com.group1.vipbilliardspayment.controller;

import com.group1.vipbilliardspayment.service.MatHangTrongHoaDonService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mathangtronghoadon")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MatHangTrongHoaDonController {
    MatHangTrongHoaDonService matHangTrongHoaDonService;
}
