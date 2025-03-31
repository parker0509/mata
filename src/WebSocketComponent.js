import React, { useEffect, useRef, useState, useCallback } from 'react';
import { useNavigate } from 'react-router-dom';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

const WebSocketComponent = ({ player }) => {
    const [players, setPlayers] = useState([]);
    const [isConnected, setIsConnected] = useState(false);
    const [messages, setMessages] = useState([]);  // 메시지 상태
    const [newMessage, setNewMessage] = useState('');  // 새 메시지 입력 상태
    const clientRef = useRef(null);
    const navigate = useNavigate();

    // 🚨 player가 없으면 로그인 페이지로 이동
    useEffect(() => {
        if (!player) {
            navigate('/');  // player 정보가 없으면 로그인 페이지로 리다이렉트
        }
    }, [player, navigate]);

    const movePlayer = useCallback((dx, dy) => {
        if (!player) return;
        console.log(`Moving player: dx=${dx}, dy=${dy}`);
        const updatedPlayer = { ...player, x: player.x + dx, y: player.y + dy };
        console.log('Updated player:', updatedPlayer);
        clientRef.current?.publish({ destination: '/app/move', body: JSON.stringify(updatedPlayer) });
    }, [player]);

    // WebSocket 연결 및 메시지 수신/전송
    useEffect(() => {
        if (!player) return;

        const client = new Client({
            brokerURL: 'ws://localhost:8080/chat',
            onConnect: () => {
                setIsConnected(true);
                console.log('WebSocket connected to server');

                // 플레이어 목록 업데이트
                client.subscribe('/topic/players', (message) => {
                    const updatedPlayer = JSON.parse(message.body);
                    setPlayers((prev) => [...prev.filter(p => p.id !== updatedPlayer.id), updatedPlayer]);
                });

                // 메시지 수신
                client.subscribe('/topic/messages', (message) => {
                    const receivedMessage = JSON.parse(message.body);
                    setMessages((prev) => [...prev, receivedMessage]);
                });

                // 서버에 플레이어 등록
                client.publish({ destination: '/app/register', body: JSON.stringify(player) });
            },
            webSocketFactory: () => new SockJS('http://localhost:8080/chat'),
        });

        clientRef.current = client;
        client.activate();

        return () => {
            clientRef.current?.deactivate();
        };
    }, [player]);

    // 메시지 전송
    const sendMessage = () => {
        if (newMessage.trim()) {
            const message = { player: player.username, content: newMessage };
            clientRef.current?.publish({ destination: '/app/chat', body: JSON.stringify(message) });
            setNewMessage('');  // 메시지 전송 후 입력창 비우기
        }
    };

    return (
        <div>
            <h2>{player?.username || '이름 없음'}의 화면</h2>

            {/* 이동 버튼 */}
            <button onClick={() => movePlayer(10, 0)}>→</button>
            <button onClick={() => movePlayer(-10, 0)}>←</button>
            <button onClick={() => movePlayer(0, 10)}>↓</button>
            <button onClick={() => movePlayer(0, -10)}>↑</button>

            <div>{isConnected ? '서버에 연결됨' : '서버에 연결되지 않음'}</div>

            <h3>채팅방</h3>
            <ul>
                {players.map((p) => (
                    <li key={p.id}>
                        {p.user ? p.user.username : p.username || '이름 없음'}
                    </li>
                ))}
            </ul>

            <h3>채팅</h3>
            <div>
                {/* 메시지 목록 */}
                {messages.map((msg, index) => (
                    <div key={index}>
                        <strong>{msg.player}:</strong> {msg.content}
                    </div>
                ))}
            </div>

            {/* 메시지 입력 */}
            <input
                type="text"
                value={newMessage}
                onChange={(e) => setNewMessage(e.target.value)}
                placeholder="메시지를 입력하세요"
            />
            <button onClick={sendMessage}>전송</button>
        </div>
    );
};

export default WebSocketComponent;
