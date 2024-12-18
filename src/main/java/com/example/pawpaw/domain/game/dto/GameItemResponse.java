package com.example.pawpaw.domain.game.dto;

import com.example.pawpaw.domain.game.entity.Game;
import com.example.pawpaw.domain.survey.entity.SurveyCategory;

import java.util.List;

public record GameItemResponse(
    int id,
    String gameName,
    String imageUrl,
    String developmentalEffect
) {
    public static List<GameItemResponse> from(List<Game> games) {
        return games.stream()
                .map(game -> new GameItemResponse(
                        game.getId(),
                        game.getName(),
                        game.getImageUrl(),
                        game.getDevelopmentalEffect().name()
                ))
                .toList();
    }

}
