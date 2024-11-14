package com.example.pawpaw.domain.survey.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SurveyCategory {
    GROSS_MOTOR_SKILLS("대근육운동"),
    FINE_MOTOR_SKILLS("소근육운동"),
    COGNITION("인지"),
    LANGUAGE("언어"),
    SOCIAL_SKILLS("사회성");

    private final String name;
}
