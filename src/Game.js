import React, { useEffect, useRef, useState } from "react";
import Phaser from "phaser";
import { Client } from "@stomp/stompjs";
import SockJS from "sockjs-client";

const Game = ({ players }) => {
  const gameRef = useRef(null);
  const playerSpritesRef = useRef({}); // 각 플레이어의 스프라이트를 저장
  const [playerData, setPlayerData] = useState({ x: 400, y: 300 }); // 기본 위치 (예: 400, 300)
  const [isConnected, setIsConnected] = useState(false); // WebSocket 연결 상태
  const [client, setClient] = useState(null); // STOMP 클라이언트

  useEffect(() => {
    // WebSocket 연결 설정
    const stompClient = new Client({
      brokerURL: "ws://localhost:8080/chat",
      onConnect: () => {
        setIsConnected(true);
        console.log("WebSocket connected");
        // WebSocket 연결 후 subscribe 설정
        stompClient.subscribe("/topic/players", (message) => {
          const updatedPlayer = JSON.parse(message.body);
          updatePlayerPosition(updatedPlayer);
        });
      },
      webSocketFactory: () => new SockJS("http://localhost:8080/chat"),
    });

    stompClient.activate();
    setClient(stompClient);

    return () => {
      stompClient.deactivate();
    };
  }, []); // 한 번만 실행되게 설정

  const updatePlayerPosition = (updatedPlayer) => {
    if (playerSpritesRef.current[updatedPlayer.playerId]) {
      playerSpritesRef.current[updatedPlayer.playerId].setPosition(updatedPlayer.x, updatedPlayer.y);
    }
  };

const [playerId] = useState(() => "player_" + Math.random().toString(36).substr(2, 9));  // ✅ ID 유지


const movePlayer = (x, y) => {
    const player = { id: playerId, x, y };

    client.publish({
        destination: "/app/move",
        body: JSON.stringify(player),
    });
};



  useEffect(() => {
    if (gameRef.current || !isConnected) return; // WebSocket 연결이 안 되면 게임 초기화 안 함

    const config = {
      type: Phaser.AUTO,
      width: 800,
      height: 600,
      parent: "game-container",
      physics: {
        default: "arcade",
        arcade: { gravity: { y: 0 }, debug: false },
      },
      scene: {
        preload: function () {
          this.load.spritesheet("character", "/character.png", {
            frameWidth: 32,
            frameHeight: 32,
          });
        },
        create: function () {
          // 기본 플레이어 생성
          this.player = this.physics.add.sprite(400, 300, "character");

          // 플레이어 애니메이션 설정
          this.anims.create({
            key: "left",
            frames: this.anims.generateFrameNumbers("character", { start: 0, end: 3 }),
            frameRate: 10,
            repeat: -1,
          });

          this.anims.create({
            key: "right",
            frames: this.anims.generateFrameNumbers("character", { start: 4, end: 7 }),
            frameRate: 10,
            repeat: -1,
          });

          this.cursors = this.input.keyboard.createCursorKeys();
        },
        update: function () {
          let moving = false;

          if (!isConnected) {
            console.log("WebSocket not connected, cannot move player");
            return; // WebSocket 연결이 안 되면 플레이어 이동 불가
          }

          // 플레이어가 좌우로 움직이는지 확인
          if (this.cursors.left.isDown) {
            this.player.setVelocityX(-160);
            this.player.anims.play("left", true);
            moving = true;
          } else if (this.cursors.right.isDown) {
            this.player.setVelocityX(160);
            this.player.anims.play("right", true);
            moving = true;
          } else {
            this.player.setVelocityX(0);
            this.player.anims.stop();
          }

          // 플레이어가 위아래로 움직이는지 확인
          if (this.cursors.up.isDown) {
            this.player.setVelocityY(-160);
            moving = true;
          } else if (this.cursors.down.isDown) {
            this.player.setVelocityY(160);
            moving = true;
          } else {
            this.player.setVelocityY(0);
          }

          // 플레이어 위치 업데이트
          if (moving) {
            setPlayerData((prev) => ({
              ...prev,
              x: this.player.x,
              y: this.player.y,
            }));
            movePlayer(this.player.x, this.player.y); // 위치 업데이트 후 서버로 보내기
          }

          // 다른 플레이어들의 위치 업데이트
          if (players && players.length > 0) {
            players.forEach((player) => {
              if (!playerSpritesRef.current[player.id]) {
                playerSpritesRef.current[player.id] = this.physics.add.sprite(player.x, player.y, "character");
              } else {
                playerSpritesRef.current[player.id].x = player.x;
                playerSpritesRef.current[player.id].y = player.y;
              }
            });
          }
        },
      },
    };

    gameRef.current = new Phaser.Game(config);

    return () => {
      gameRef.current.destroy(true);
      gameRef.current = null;
    };
  }, [isConnected, players]); // WebSocket 연결 상태와 players 배열이 변경될 때마다 useEffect 실행

  return <div id="game-container" />;
};

export default Game;
