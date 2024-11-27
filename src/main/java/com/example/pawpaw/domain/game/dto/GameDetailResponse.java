package com.example.pawpaw.domain.game.dto;

import com.example.pawpaw.domain.game.entity.Game;

public record GameDetailResponse(
    int id,
    String name,
    String imageUrl,
    String developmentalEffect,
    String description,
    String steps,
    String materials,
    int minAgeMonths,
    int maxAgeMonths
) {
    public static GameDetailResponse from(Game game) {
        return new GameDetailResponse(
            game.getId(),
            game.getName(),
            game.getImageUrl(),
            game.getDevelopmentalEffect().getName(),
            game.getDescription(),
            game.getSteps(),
            game.getMaterials(),
            game.getMinAgeMonths(),
            game.getMaxAgeMonths()
        );
    }
}
