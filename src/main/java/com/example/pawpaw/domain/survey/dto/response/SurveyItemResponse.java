package com.example.pawpaw.domain.survey.dto.response;

public record SurveyItemResponse(
    int surveyId,
    String title,
    int minAgeMonths,
    int maxAgeMonths
) {
}
