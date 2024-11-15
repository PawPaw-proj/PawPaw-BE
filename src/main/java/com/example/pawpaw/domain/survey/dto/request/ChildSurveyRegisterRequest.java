package com.example.pawpaw.domain.survey.dto.request;

import java.util.List;

public record ChildSurveyRegisterRequest(
    List<SurveyCategoryResponse> data
) {
}
