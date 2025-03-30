package com.example.mata.mata.controller;

import com.example.mata.mata.domain.Player;
import com.example.mata.mata.domain.User;
import com.example.mata.mata.dto.UserDto;
import com.example.mata.mata.repository.PlayerRepository;
import com.example.mata.mata.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthController() {
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
        System.out.println("Login attempt with username: " + userDto.getUsername());
        // 사용자 인증 로직 추가
        Optional<User> user = userRepository.findByUsername(userDto.getUsername());
        if (user.isPresent() && passwordEncoder.matches(userDto.getPassword(), user.get().getPassword())) {
            System.out.println("Login successful");
            return ResponseEntity.ok(user.get());
        } else {
            System.out.println("Login failed");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
