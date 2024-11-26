package com.example.pawpaw.domain.game.controller;

import com.example.pawpaw.domain.game.dto.GameDetailResponse;
import com.example.pawpaw.domain.game.dto.GameListResponse;
import com.example.pawpaw.domain.game.service.GameService;
import com.example.pawpaw.global.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping("/games")
    public Response<GameListResponse> getGames(@RequestParam int childId) {
        GameListResponse response = gameService.getGames(childId);
        return Response.success(response);
    }

    @GetMapping("/recommended-games")
    public Response<GameListResponse> getRecommendedGames(@RequestParam int childId) {
        GameListResponse response = gameService.getRecommendedGames(childId);
        return Response.success(response);
    }

    @GetMapping("/games/{gameId}")
    public Response<GameDetailResponse> getGameDetail(@PathVariable int gameId) {
        GameDetailResponse response = gameService.getGameDetail(gameId);
        return Response.success(response);
    }
}
