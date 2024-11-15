package com.example.pawpaw.domain.survey.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum SurveyCategory {
    GROSS_MOTOR_SKILLS("대근육운동"),
    FINE_MOTOR_SKILLS("소근육운동"),
    COGNITION("인지"),
    LANGUAGE("언어"),
    SOCIAL_SKILLS("사회성");

    private final String name;

    public static SurveyCategory from(String name) {
        return Stream.of(values())
                .filter(category -> category.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 문진 카테고리입니다: " + name));
    }
}
