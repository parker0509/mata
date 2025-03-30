package com.example.mata.mata.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // 비밀번호 암호화
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // CSRF 비활성화
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/login", "/api/register", "/api/user/**", "/api/player/**").permitAll() // GET 요청도 허용
                        .requestMatchers("/chat/**").permitAll() // 🔹 WebSocket 허용
                        .anyRequest().authenticated() // 나머지는 인증 필요
                )
                .formLogin(login -> login.disable()) // 기본 로그인 폼 사용 안 함
                .httpBasic(basic -> basic.disable()); // 기본 HTTP Basic 인증 비활성화
        return http.build();
    }
}
