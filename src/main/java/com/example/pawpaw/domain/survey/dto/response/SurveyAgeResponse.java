package com.example.pawpaw.domain.survey.dto.response;

import com.example.pawpaw.domain.survey.entity.ChildSurvey;

public record SurveyAgeResponse(
    int months,
    int days
) {
    public static SurveyAgeResponse from(ChildSurvey childSurvey) {
        return new SurveyAgeResponse(
            childSurvey.getSurveyAgeMonths(),
            childSurvey.calculateChildAgeByDays()
        );
    }
}
