package com.example.pawpaw.domain.survey.dto;

public record CutoffScoresResponse(
    int low,
    int medium,
    int high
) {
}
