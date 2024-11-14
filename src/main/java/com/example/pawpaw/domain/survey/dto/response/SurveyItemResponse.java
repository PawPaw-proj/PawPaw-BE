package com.example.pawpaw.domain.survey.dto.response;

public record SurveyItemResponse(
    Long surveyId,
    String title,
    int minAgeMonths,
    int maxAgeMonths
) {
}
