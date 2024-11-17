package com.example.pawpaw.domain.survey.dto.response;

public record CutoffScoresResponse(
    int low,
    int medium,
    int high
) {
}
