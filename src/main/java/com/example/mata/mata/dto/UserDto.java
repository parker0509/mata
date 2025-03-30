package com.example.mata.mata.dto;

import lombok.Getter;

@Getter
public class UserDto {
    private String username;
    private String password;

    public UserDto() {}

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
