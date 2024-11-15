package com.example.pawpaw.domain.survey.dto.request;

import java.util.List;

public record SurveyCategoryResponse(
    String category,
    List<Integer> surveyResponses
) {
}
