package com.group1.vipbilliardspayment.controller;

import com.group1.vipbilliardspayment.dto.request.AuthenticationRequest;
import com.group1.vipbilliardspayment.dto.request.IntrospectRequest;
import com.group1.vipbilliardspayment.dto.response.ApiResponse;
import com.group1.vipbilliardspayment.dto.response.AuthenticationResponse;
import com.group1.vipbilliardspayment.dto.response.IntrospectResponse;
import com.group1.vipbilliardspayment.dto.response.ThuNganResponse;
import com.group1.vipbilliardspayment.entity.ThuNgan;
import com.group1.vipbilliardspayment.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authentication")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/chuquantoken")
    public ApiResponse<AuthenticationResponse> chuQuanAuthentication(@RequestBody @Valid AuthenticationRequest request) {
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationService.chuQuanAuthentication(request))
                .build();
    }

    @PostMapping("/thungantoken")
    public ApiResponse<AuthenticationResponse> thuNganAuthentication(@RequestBody @Valid AuthenticationRequest request) {
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationService.thuNganAuthentication(request))
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> authenticate(@RequestBody @Valid IntrospectRequest request) throws ParseException, JOSEException {
        return ApiResponse.<IntrospectResponse>builder()
                .result(authenticationService.introspect(request))
                .build();
    }

    @GetMapping("/thungan/profile")
    public ApiResponse<ThuNganResponse> getThuNganProfile() {
        return ApiResponse.<ThuNganResponse>builder()
                .result(authenticationService.getThuNganProfile())
                .build();
    }
}
