/*
package com.example.mata.mata.controller;

import com.example.mata.mata.domain.Player;
import com.example.mata.mata.service.PlayerService;  // playerService를 사용하려면 서비스 클래스가 필요
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller  // Spring이 이 클래스를 컨트롤러로 인식
public class MovePlayerController {

    @Autowired
    private PlayerService playerService;  // 플레이어 서비스 주입

    @MessageMapping("/game/move")  // 경로를 변경
    @SendTo("/topic/players")  // 반환되는 리스트는 /topic/players로 구독된 클라이언트에 전송됩니다.
    public List<Player> move(Player player) {
        // 플레이어 이동 처리
        playerService.movePlayer(player);  // 플레이어 이동 처리 메서드 호출
        return playerService.getUpdatedPlayers();  // 업데이트된 플레이어 리스트 반환
    }
}

*/
