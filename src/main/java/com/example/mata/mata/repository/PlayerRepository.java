package com.example.mata.mata.repository;

import com.example.mata.mata.domain.Player;
import com.example.mata.mata.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long> {

    Optional<Player> findByUser(User user);  // 🔹 Optional로 반환

}
