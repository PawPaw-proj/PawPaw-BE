package com.example.pawpaw.domain.survey.service.event;

import java.util.List;

public record ChildSurveyRegisteredEvent(
    int surveyId,
    List<Integer> surveyResponses
) {

}
