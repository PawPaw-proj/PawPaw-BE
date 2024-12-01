package com.example.pawpaw.domain.game.service;

import com.example.pawpaw.domain.auth.service.AuthService;
import com.example.pawpaw.domain.child.repository.ChildRepository;
import com.example.pawpaw.domain.game.dto.GameDetailResponse;
import com.example.pawpaw.domain.game.dto.GameListResponse;
import com.example.pawpaw.domain.game.entity.Game;
import com.example.pawpaw.domain.game.repository.GameRepository;
import com.example.pawpaw.domain.survey.dto.SurveyResult;
import com.example.pawpaw.domain.survey.repository.ChildSurveyRepository;
import com.example.pawpaw.domain.survey.service.SurveyService;
import com.example.pawpaw.global.response.CustomException;
import com.example.pawpaw.global.response.ErrorCode;
import com.example.pawpaw.global.util.ai.GptApiClient;
import com.example.pawpaw.global.util.ai.RecommendGameResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final ChildRepository childRepository;
    private final ChildSurveyRepository childSurveyRepository;
    private final AuthService authService;
    private final GptApiClient gptApiClient;
    private final SurveyService surveyService;

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
        var recentChildSurvey = childSurveyRepository.findLatestByChildId(childId)
            .orElseThrow(() -> new IllegalArgumentException("문진표 검사 기록이 없기 때문에 적절한 놀이를 추천을 할 수 없습니다."));
        SurveyResult surveyResult = surveyService.getSurveyAverages(recentChildSurvey.getId());
        var surveyCategoriesToImprove = recentChildSurvey.getSurveyCategoriesToImprove(surveyResult.areas());

        List<RecommendGameResponseDTO> recommendedGamesResponse = gptApiClient.askRecommendedGames(surveyCategoriesToImprove);
        List<Game> recommendedGames = new ArrayList<>();
        for (RecommendGameResponseDTO gameResponse : recommendedGamesResponse) {
            gameRepository.findById(gameResponse.id())
                .ifPresent(recommendedGames::add);
        }
        return GameListResponse.from(child, recommendedGames);
    }

    public GameDetailResponse getGameDetail(int gameId) {
        var game = gameRepository.findById(gameId)
            .orElseThrow(() -> new IllegalArgumentException("게임을 찾을 수 없습니다."));
        return GameDetailResponse.from(game);
    }
}
