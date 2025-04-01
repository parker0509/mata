/*
package com.example.mata.mata.service;

import com.example.mata.mata.domain.Player;
import com.example.mata.mata.domain.User;
import com.example.mata.mata.repository.PlayerRepository;
import com.example.mata.mata.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PlayerRepository playerRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.playerRepository = playerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<Player> findPlayerByUsername(String username) {
        return userRepository.findByUsername(username)
                .flatMap(playerRepository::findByUser);
    }
}
*/
