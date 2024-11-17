package com.example.pawpaw.domain.survey.dto.response;

import com.example.pawpaw.domain.survey.entity.ChildSurvey;
import com.example.pawpaw.domain.survey.entity.Survey;

import java.time.LocalDate;
import java.util.List;

public record ChildSurveyResponse(
    int childSurveyId,
    String title,
    LocalDate surveyDate,
    SurveyAgeResponse ageAtSurvey,
    List<SurveyScoreResponse> scores,
    String evaluationResult
) {
    public static ChildSurveyResponse from(ChildSurvey childSurvey) {
        Survey survey = Survey.findById(childSurvey.getSurveyId());
        return new ChildSurveyResponse(
            childSurvey.getId(),
            survey.getTitle(),
            childSurvey.getSurveyDate(),
            SurveyAgeResponse.from(childSurvey),
            SurveyScoreResponse.from(childSurvey),
            childSurvey.getEvaluationResult()
        );
    }
}
