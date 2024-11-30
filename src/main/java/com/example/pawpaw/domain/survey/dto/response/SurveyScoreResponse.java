package com.example.pawpaw.domain.survey.dto.response;

import com.example.pawpaw.domain.survey.entity.ChildSurvey;
import com.example.pawpaw.domain.survey.entity.SurveyCategory;
import com.example.pawpaw.domain.survey.entity.Survey;

import java.util.List;

public record SurveyScoreResponse(
    String category,
    int score,
    CutoffScoresResponse cutoffScores
) {
    public static List<SurveyScoreResponse> from(ChildSurvey childSurvey) {
        Survey survey = Survey.findById(childSurvey.getSurveyId());
        return childSurvey.getSurveySections().stream()
                .map(section -> {
                    SurveyCategory category = section.getCategory();
                    return new SurveyScoreResponse(
                            section.getCategory().getName(),
                            section.calculateTotalScore(),
                            new CutoffScoresResponse(
                                    survey.getScoreThresholds().get(category).get("가"),
                                    survey.getScoreThresholds().get(category).get("나"),
                                    survey.getScoreThresholds().get(category).get("다")
                            )
                    );
                })
                .toList();
    }
}
