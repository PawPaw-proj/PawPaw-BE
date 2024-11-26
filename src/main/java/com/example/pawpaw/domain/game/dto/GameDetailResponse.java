package com.example.pawpaw.domain.game.dto;

import com.example.pawpaw.domain.game.entity.Game;
import com.example.pawpaw.domain.survey.entity.SurveyCategory;

import java.util.List;
import java.util.Optional;

public record GameDetailResponse(
    int id,
    String name,
    String imageUrl,
    List<String> developmentalEffects,
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
            game.getDevelopmentalEffects().stream().map(SurveyCategory::getName).toList(),
            game.getDescription(),
            game.getSteps(),
            game.getMaterials(),
            game.getMinAgeMonths(),
            game.getMaxAgeMonths()
        );
    }
}
