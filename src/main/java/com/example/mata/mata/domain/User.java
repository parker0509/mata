package com.example.mata.mata.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class User {
    // Getter & Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;  // 사용자 이름 (고유)

    @Column(nullable = false)
    private String password;  // 비밀번호

    // 기본 생성자
    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
