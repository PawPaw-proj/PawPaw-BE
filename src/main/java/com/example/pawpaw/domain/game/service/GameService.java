package com.example.pawpaw.domain.game.service;

import com.example.pawpaw.domain.auth.service.AuthService;
import com.example.pawpaw.domain.child.repository.ChildRepository;
import com.example.pawpaw.domain.game.dto.GameDetailResponse;
import com.example.pawpaw.domain.game.dto.GameListResponse;
import com.example.pawpaw.domain.game.repository.GameRepository;
import com.example.pawpaw.global.response.CustomException;
import com.example.pawpaw.global.response.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final ChildRepository childRepository;
    private final AuthService authService;

    public GameListResponse getGames(int childId) {
        if (!authService.isParentOfChild(childId)) {
            throw new CustomException(ErrorCode.BAD_REQUEST_CHILD);
        }
        var child = childRepository.findById(childId);
        var games = gameRepository.findAll();
        return GameListResponse.from(child, games);
    }

    public GameListResponse getRecommendedGames(int childId) {
        if (!authService.isParentOfChild(childId)) {
            throw new CustomException(ErrorCode.BAD_REQUEST_CHILD);
        }

        var child = childRepository.findById(childId);
        var games = gameRepository.findAll();
        // TODO: 추천한 게임으로 대체
        return GameListResponse.from(child, games);
    }

    public GameDetailResponse getGameDetail(int gameId) {
        var game = gameRepository.findById(gameId)
            .orElseThrow(() -> new IllegalArgumentException("게임을 찾을 수 없습니다."));
        return GameDetailResponse.from(game);
    }
}
