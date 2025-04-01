//package com.example.mata.mata.dto;
//
//import com.example.mata.mata.domain.Player;
//import lombok.Getter;
//
//@Getter
//public class PlayerResponseDto {
//    private Long id;
//    private String username;
//    private int x;
//    private int y;
//
//    public PlayerResponseDto(Player player) {
//        this.id = player.getId();
//        // player.getUser()가 null일 경우 "이름 없음"으로 처리
//        this.username = (player.getUser() != null) ? player.getUser().getUsername() : "이름 없음";
//        this.x = player.getX();
//        this.y = player.getY();
//    }
//
//    // Getters and Setters
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setX(int x) {
//        this.x = x;
//    }
//
//    public void setY(int y) {
//        this.y = y;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public int getX() {
//        return x;
//    }
//
//    public int getY() {
//        return y;
//    }
//}
