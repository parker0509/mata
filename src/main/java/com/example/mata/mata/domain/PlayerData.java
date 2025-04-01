package com.example.mata.mata.domain;


import lombok.Getter;


@Getter
public class PlayerData {
    private String playerId;
    private double x;
    private double y;


    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
