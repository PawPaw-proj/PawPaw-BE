package com.example.pawpaw.domain.game.dto;

import com.example.pawpaw.domain.game.entity.Game;
import com.example.pawpaw.domain.survey.entity.SurveyCategory;

import java.util.List;

public record GameItemResponse(
    String gameName,
    String imageUrl,
    List<String> developmentalEffects
) {
    public static List<GameItemResponse> from(List<Game> games) {
        return games.stream()
                .map(game -> new GameItemResponse(
                        game.getName(),
                        game.getImageUrl(),
                        game.getDevelopmentalEffects().stream().map(SurveyCategory::getName).toList()
                ))
                .toList();
    }

}
