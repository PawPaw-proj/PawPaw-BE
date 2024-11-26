package com.example.pawpaw.domain.game.repository;

import com.example.pawpaw.domain.game.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {
}
