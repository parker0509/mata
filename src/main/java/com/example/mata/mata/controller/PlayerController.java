/*
package com.example.mata.mata.controller;

import com.example.mata.mata.domain.Player;
import com.example.mata.mata.repository.PlayerRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@NoArgsConstructor
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping("/player/{playerId}")
    public ResponseEntity<Player> getPlayer(@PathVariable Long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        // Player의 User 정보 (username) 가져오기
        String username = player.getUser().getUsername();
        System.out.println("Player's username: " + username);

        return ResponseEntity.ok(player);
    }


}
*/
