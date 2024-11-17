package com.example.pawpaw.domain.survey.dto;

import com.example.pawpaw.domain.survey.entity.questions.Question;

import java.util.List;

public record SurveyQuestionResponse(
    int sequence,
    String question,
    String imageUrl
) {
    public static List<SurveyQuestionResponse> from(List<Question> questions) {
        return questions.stream()
            .map(question -> new SurveyQuestionResponse(
                question.sequence(),
                question.question(),
                question.imageUrl()
            ))
            .toList();
    }
}
