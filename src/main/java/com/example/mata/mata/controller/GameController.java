package com.example.mata.mata.controller;

import com.example.mata.mata.domain.Player;
import com.example.mata.mata.repository.PlayerRepository;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
    @Controller
    class GameController {

        private final SimpMessagingTemplate messagingTemplate;

        public GameController(SimpMessagingTemplate messagingTemplate) {
            this.messagingTemplate = messagingTemplate;
        }


        @MessageMapping("/move")  // 클라이언트에서 이동 요청을 받음
        @SendTo("/topic/players")  // 해당 메시지를 /topic/players로 모든 클라이언트에게 전달
        public Player movePlayer(@RequestBody Player player) {
            // 플레이어의 ID와 위치 정보 처리
            System.out.println("Received move request for player " + player.getId() + ": " + player.getX() + ", " + player.getY());
            return player;  // 이동 정보를 포함하여 다른 클라이언트로 전송
        }


        @MessageMapping("/register") // 플레이어가 서버에 접속했을 때
        public void registerPlayer(@RequestBody Player player) {
            // 새로운 플레이어 정보 클라이언트들에게 전송
            messagingTemplate.convertAndSend("/topic/players", player);
        }

}