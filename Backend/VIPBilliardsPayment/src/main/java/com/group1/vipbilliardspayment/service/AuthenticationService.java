package com.group1.vipbilliardspayment.service;

import com.group1.vipbilliardspayment.dto.request.AuthenticationRequest;
import com.group1.vipbilliardspayment.dto.request.IntrospectRequest;
import com.group1.vipbilliardspayment.dto.response.AuthenticationResponse;
import com.group1.vipbilliardspayment.dto.response.IntrospectResponse;
import com.group1.vipbilliardspayment.entity.ChuQuan;
import com.group1.vipbilliardspayment.entity.NguoiDung;
import com.group1.vipbilliardspayment.entity.ThuNgan;
import com.group1.vipbilliardspayment.exception.AppException;
import com.group1.vipbilliardspayment.exception.ErrorCode;
import com.group1.vipbilliardspayment.repository.ChuQuanRepository;
import com.group1.vipbilliardspayment.repository.ThuNganRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    ChuQuanRepository chuQuanRepository;
    ThuNganRepository thuNganRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    String signerKey;

    public AuthenticationResponse chuQuanAuthentication(AuthenticationRequest request) {
        var chuQuan = chuQuanRepository.findByTenDangNhap(request.getTenDangNhap()).orElseThrow(() -> new AppException(ErrorCode.CHUQUAN_TENDANGNHAP_NOT_EXISTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean valid = passwordEncoder.matches(request.getMatKhau(), chuQuan.getMatKhau());

        if(!valid) {
            throw new AppException(ErrorCode.MATKHAU_WRONG);
        }

        return AuthenticationResponse.builder()
                .token(generateToken(chuQuan))
                .build();
    }

    public AuthenticationResponse thuNganAuthentication(AuthenticationRequest request) {
        var thuNgan = thuNganRepository.findByTenDangNhap(request.getTenDangNhap()).orElseThrow(() -> new AppException(ErrorCode.THUNGAN_TENDANGNHAP_NOT_EXISTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean valid = passwordEncoder.matches(request.getMatKhau(), thuNgan.getMatKhau());

        if(!valid) {
            throw new AppException(ErrorCode.MATKHAU_WRONG);
        }

        return AuthenticationResponse.builder()
                .token(generateToken(thuNgan))
                .build();
    }

    public String generateToken(NguoiDung nguoiDung) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        String role;
        if(nguoiDung instanceof ChuQuan) role = ChuQuan.getRole();
        else role = ThuNgan.getRole();

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(nguoiDung.getTenDangNhap())
                .issuer("vipbilliards.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .claim("scope", role)
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner((signerKey.getBytes())));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    public IntrospectResponse introspect(IntrospectRequest request) throws ParseException, JOSEException {
        var token = request.getToken();

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        JWSVerifier jwsVerifier = new MACVerifier(signerKey.getBytes());

        boolean valid = signedJWT.verify(jwsVerifier) && expiryTime.after(new Date());

        return IntrospectResponse.builder()
                .valid(valid)
                .build();
    }

}
