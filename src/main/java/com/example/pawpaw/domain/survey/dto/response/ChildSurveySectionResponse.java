package com.example.pawpaw.domain.survey.dto.response;

import com.example.pawpaw.domain.survey.entity.SurveySection;

import java.util.List;

public record ChildSurveySectionResponse(
    int totalScore,
    List<Integer> surveyResponses
) {
    public static ChildSurveySectionResponse from(SurveySection section) {
        return new ChildSurveySectionResponse(
            section.calculateTotalScore(),
            section.getResponses()
        );
    }
}
