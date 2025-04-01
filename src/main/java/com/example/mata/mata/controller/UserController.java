/*
package com.example.mata.mata.controller;


import com.example.mata.mata.dto.UserDto;
import com.example.mata.mata.domain.User;
import com.example.mata.mata.domain.Player;
import com.example.mata.mata.repository.UserRepository;
import com.example.mata.mata.repository.PlayerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PlayerRepository playerRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.playerRepository = playerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ 회원가입 API
    @Transactional
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserDto userDto) {
        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // 2. Player 생성 (초기 위치 x=0, y=0)
        Player player = new Player(newUser, 0, 0);
        playerRepository.save(player);

        return ResponseEntity.ok(newUser);
    }

    // ✅ 플레이어 등록 API
    @PostMapping("/register/player")
    public Player registerPlayer(@RequestBody PlayerResponseDto playerResponseDto) {
        Optional<User> user = userRepository.findById(playerResponseDto.getId());
        if (user.isPresent()) {
            Player newPlayer = new Player();
            newPlayer.setX(playerResponseDto.getX());
            newPlayer.setY(playerResponseDto.getY());
            return playerRepository.save(newPlayer);
        }
        throw new RuntimeException("User not found");
    }

    @GetMapping("/user/{username}")  // 변경
    public Optional<User> findUserByUsername(@PathVariable("username") String username) {
        return userRepository.findByUsername(username);
    }

    @GetMapping("/player/{username}")  // 변경
    public Optional<Player> findPlayerByUsername(@PathVariable("username") String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.flatMap(playerRepository::findByUser);
    }


}
*/
