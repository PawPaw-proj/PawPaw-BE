package com.example.pawpaw.domain.survey.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum SurveyCategory {
    GROSS_MOTOR_SKILLS("대근육운동", "CTG-001"),
    FINE_MOTOR_SKILLS("소근육운동", "CTG-002"),
    COGNITION("인지", "CTG-003"),
    LANGUAGE("언어", "CTG-004"),
    SOCIAL_SKILLS("사회성", "CTG-005"),
    SELF_CARE("자조", "CTG-006");

    private final String name;
    private final String code;

    public static SurveyCategory from(String name) {
        return Stream.of(values())
                .filter(category -> category.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 문진 카테고리입니다: " + name));
    }

    public static SurveyCategory fromCode(String code) {
        return Stream.of(values())
                .filter(category -> category.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 문진 카테고리 코드입니다: " + code));
    }
}
