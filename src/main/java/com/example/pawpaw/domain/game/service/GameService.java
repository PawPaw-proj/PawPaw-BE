package com.example.pawpaw.domain.game.service;

import com.example.pawpaw.domain.auth.service.AuthService;
import com.example.pawpaw.domain.child.repository.ChildRepository;
import com.example.pawpaw.domain.game.dto.GameDetailResponse;
import com.example.pawpaw.domain.game.dto.GameListResponse;
import com.example.pawpaw.domain.game.repository.GameRepository;
import com.example.pawpaw.domain.survey.entity.Survey;
import com.example.pawpaw.domain.survey.repository.ChildSurveyRepository;
import com.example.pawpaw.global.response.CustomException;
import com.example.pawpaw.global.response.ErrorCode;
import com.example.pawpaw.global.util.ai.GptApiClient;
import com.example.pawpaw.global.util.ai.GptApiClient2;
import com.example.pawpaw.global.util.ai.RecommendGameResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        var survey = Survey.findById(recentChildSurvey.getSurveyId());
        var surveyCategoriesToImprove = recentChildSurvey.getSurveyCategoriesToImprove(survey);

        List<RecommendGameResponseDTO> recommendedGamesResponse = gptApiClient.askRecommendedGames(surveyCategoriesToImprove);
        var recommendedGameIds = recommendedGamesResponse.stream()
            .map(RecommendGameResponseDTO::id)
            .toList();
        var recommendedGames = gameRepository.findAllByIdIn(recommendedGameIds);
        return GameListResponse.from(child, recommendedGames);
    }

    public GameDetailResponse getGameDetail(int gameId) {
        var game = gameRepository.findById(gameId)
            .orElseThrow(() -> new IllegalArgumentException("게임을 찾을 수 없습니다."));
        return GameDetailResponse.from(game);
    }
}
