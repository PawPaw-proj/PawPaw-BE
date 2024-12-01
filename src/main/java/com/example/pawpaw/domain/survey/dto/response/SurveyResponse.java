package com.example.pawpaw.domain.survey.dto.response;

import com.example.pawpaw.domain.survey.entity.SurveyCategory;
import com.example.pawpaw.domain.survey.entity.Survey;
import com.example.pawpaw.domain.survey.entity.questions.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record SurveyResponse(
    int surveyId,
    List<SurveyQuestionResponse> questions
) {
    public static SurveyResponse from(Survey survey) {
        int idx = 0;
        List<SurveyQuestionResponse> questions = new ArrayList<>();
        for (Map.Entry<SurveyCategory, List<Question>> entry : survey.getQuestionsByCategory().entrySet()) {
            for (Question question : entry.getValue()) {
                questions.add(new SurveyQuestionResponse(question.sequence() + idx, question.question(), question.imageUrl()));
            }
            idx += 8;
        }

        return new SurveyResponse(survey.getId(), questions);
    }
}
