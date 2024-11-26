package com.example.pawpaw.domain.game.dto;

import com.example.pawpaw.domain.child.entity.Child;
import com.example.pawpaw.domain.game.entity.Game;
import com.example.pawpaw.domain.survey.dto.response.ChildInfoResponse;

import java.util.List;

public record GameListResponse(
        ChildInfoResponse child,
        List<GameItemResponse> games
) {
    public static GameListResponse from(Child child, List<Game> games) {
        var childInfo = ChildInfoResponse.from(child);
        var gameInfos = GameItemResponse.from(games);
        return new GameListResponse(childInfo, gameInfos);
    }
}
