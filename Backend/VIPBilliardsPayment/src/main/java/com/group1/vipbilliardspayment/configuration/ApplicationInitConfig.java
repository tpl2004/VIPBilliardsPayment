package com.group1.vipbilliardspayment.configuration;

import com.group1.vipbilliardspayment.entity.ChuQuan;
import com.group1.vipbilliardspayment.repository.ChuQuanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
public class ApplicationInitConfig {

    @Bean
    ApplicationRunner applicationRunner(ChuQuanRepository chuQuanRepository) {
        return args -> {
            if(chuQuanRepository.findByTenDangNhap("admin").isEmpty()) {
                ChuQuan chuQuan = new ChuQuan();
                chuQuan.setTenDangNhap("admin");
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
                chuQuan.setMatKhau(passwordEncoder.encode("admin"));
                chuQuanRepository.save(chuQuan);

                log.warn("admin account has just been created");
            }
        };
    }
}
