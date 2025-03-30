package com.example.mata.mata.controller;

import com.example.mata.mata.domain.Player;
import com.example.mata.mata.domain.User;
import com.example.mata.mata.repository.PlayerRepository;
import com.example.mata.mata.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@NoArgsConstructor
public class GameController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PlayerRepository playerRepository;

    // 각 플레이어의 위치를 저장할 맵 (Player ID를 키로 사용)
    private Map<Long, Player> players = new ConcurrentHashMap<>();

    @MessageMapping("/move")
    @SendTo("/topic/players")
    public Player move(Player player) {
        // player의 user_id를 사용해 User를 찾고 설정
        User user = userRepository.findById(player.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        player.setUser(user);

        // player ID로 위치를 업데이트
        players.put(player.getId(), player);
        return player;
    }

    @MessageMapping("/register")
    public void registerPlayer(Player player) {
        Optional<User> user = userRepository.findById(player.getId());
        if (user.isPresent()) {
            player.setUser(user.get());  // User 정보를 player에 설정
            playerRepository.save(player);
        } else {
            throw new RuntimeException("User not found");
        }
    }

}