package com.example.pawpaw.domain.survey.dto;

import com.example.pawpaw.domain.survey.entity.ChildSurvey;
import com.example.pawpaw.domain.survey.entity.Survey;

import java.time.LocalDate;
import java.util.List;

public record ChildSurveyItemResponse(
    int childSurveyId,
    String title,
    int minAgeMonths,
    int maxAgeMonths,
    LocalDate surveyDate
) {

    public static List<ChildSurveyItemResponse> from(List<ChildSurvey> childSurveys) {
        return childSurveys.stream()
            .map(childSurvey -> {
                Survey survey = Survey.findById(childSurvey.getSurveyId());
                return new ChildSurveyItemResponse(
                    childSurvey.getId(),
                    survey.getTitle(),
                    survey.getMinAgeMonths(),
                    survey.getMaxAgeMonths(),
                    childSurvey.getSurveyDate()
                );
            })
            .toList();
    }
}
