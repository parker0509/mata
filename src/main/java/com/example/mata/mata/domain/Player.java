package com.example.mata.mata.domain;

import jakarta.persistence.*;
import lombok.Getter;


@Getter
@Entity
public class Player {
    // Getter & Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int x;
    private int y;

/*
    Optimistic Locking은 충돌이 발생할 때, 예외를 던져서 해당 트랜잭션이 실패하도록 하고,
     이를 처리하는 방식입니다. 비관적 락에 비해 성능이 좋고,
      동시에 여러 사용자가 데이터에 접근할 수 있게 합니다.*/

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", unique = true)  // User와 1:1 관계
    private User user;

    // 기본 생성자
    public Player() {}

    public Player(User user, int x, int y) {
        this.user = user;
        this.x = x;
        this.y = y;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setUser(User user) {
        this.user = user;
    }
}