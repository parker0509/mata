import React from "react";
import Game from "./Game";

function App() {
    return (
        <div>
            <h1>메타버스 채팅</h1>
            <Game />
        </div>
    );
}

export default App;




/*
import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Login from './Login';
import Register from './Register';
import WebSocketComponent from './WebSocketComponent';

function App() {
    const [player, setPlayer] = useState(null); // 로그인 성공 시 플레이어 정보 저장

    return (
        <Router>
            <Routes>
                <Route path="/login" element={<Login onLogin={setPlayer} />} />
                <Route path="/register" element={<Register />} />
                <Route
                    path="/game"
                    element={player ? <WebSocketComponent player={player} /> : <Navigate to="/login" />}
                />
            </Routes>
        </Router>
    );
}

export default App;
*/
