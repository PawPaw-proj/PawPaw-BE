package com.example.pawpaw.domain.survey.dto.response;

import com.example.pawpaw.domain.survey.entity.questions.Question;

import java.util.List;

public record SurveyQuestionResponse(
    int sequence,
    String question,
    String imageUrl
) {

}
