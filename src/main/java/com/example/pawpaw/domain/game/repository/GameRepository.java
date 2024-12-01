package com.example.pawpaw.domain.game.repository;

import com.example.pawpaw.domain.game.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {
    List<Game> findAllByIdIn(List<Integer> recommendedGameIds);
}
