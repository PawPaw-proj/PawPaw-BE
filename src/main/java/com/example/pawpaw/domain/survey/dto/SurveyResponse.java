package com.example.pawpaw.domain.survey.dto;

import com.example.pawpaw.domain.survey.entity.Survey;
import com.example.pawpaw.domain.survey.entity.SurveyCategory;

import java.util.List;

public record SurveyResponse(
    int surveyId,
    List<SurveyQuestionResponse> grossMotorSkills,
    List<SurveyQuestionResponse> fineMotorSkills,
    List<SurveyQuestionResponse> cognition,
    List<SurveyQuestionResponse> language,
    List<SurveyQuestionResponse> socialSkills
) {
    public static SurveyResponse from(Survey survey) {
        var grossMotorSkills = SurveyQuestionResponse.from(survey.getQuestionsByCategory().get(SurveyCategory.GROSS_MOTOR_SKILLS));
        var fineMotorSkills = SurveyQuestionResponse.from(survey.getQuestionsByCategory().get(SurveyCategory.FINE_MOTOR_SKILLS));
        var cognition = SurveyQuestionResponse.from(survey.getQuestionsByCategory().get(SurveyCategory.COGNITION));
        var language = SurveyQuestionResponse.from(survey.getQuestionsByCategory().get(SurveyCategory.LANGUAGE));
        var socialSkills = SurveyQuestionResponse.from(survey.getQuestionsByCategory().get(SurveyCategory.SOCIAL_SKILLS));
        return new SurveyResponse(survey.getId(), grossMotorSkills, fineMotorSkills, cognition, language, socialSkills);
    }
}
