package com.example.mata.mata.service;

import com.example.mata.mata.domain.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class PlayerService {
    private final List<Player> players = new ArrayList<>();

    // 플레이어 추가 (예시)
    public void addPlayer(Player player) {
        players.add(player);
    }

    // 플레이어 이동 처리
    public void movePlayer(Player player) {
        Player existingPlayer = players.stream()
                .filter(p -> p.getId().equals(player.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Player not found"));

        // 플레이어의 새로운 위치 갱신
        existingPlayer.setX(player.getX());
        existingPlayer.setY(player.getY());
    }

    // 모든 플레이어 리스트 반환
    public List<Player> getUpdatedPlayers() {
        return players;
    }
}

