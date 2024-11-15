package com.example.pawpaw.domain.survey.entity;

import lombok.Getter;

import java.util.Map;
import java.util.stream.Stream;

import static com.example.pawpaw.domain.survey.entity.SurveyCategory.*;

@Getter
public enum Survey {
    SURVEY1(1, "발달선별검사 1차", 4, 5, Map.of(
        GROSS_MOTOR_SKILLS, Map.of("가", 9, "나", 14, "다", 20),
        FINE_MOTOR_SKILLS, Map.of("가", 11, "나", 16, "다", 24),
        COGNITION, Map.of("가", 10, "나", 14, "다", 20),
        LANGUAGE, Map.of("가", 9, "나", 16, "다", 23),
        SOCIAL_SKILLS, Map.of("가", 11, "나", 15, "다", 22)
    )),
    SURVEY2(2, "발달선별검사 2차", 6, 7, Map.of(
        GROSS_MOTOR_SKILLS, Map.of("가", 7, "나", 12, "다", 21),
        FINE_MOTOR_SKILLS, Map.of("가", 14, "나", 19, "다", 24),
        COGNITION, Map.of("가", 13, "나", 17, "다", 23),
        LANGUAGE, Map.of("가", 11, "나", 16, "다", 24),
        SOCIAL_SKILLS, Map.of("가", 13, "나", 15, "다", 22)
    )),
    SURVEY3(3, "발달선별검사 3차", 8, 9, Map.of(
        GROSS_MOTOR_SKILLS, Map.of("가", 5, "나", 15, "다", 24),
        FINE_MOTOR_SKILLS, Map.of("가", 16, "나", 20, "다", 24),
        COGNITION, Map.of("가", 15, "나", 19, "다", 24),
        LANGUAGE, Map.of("가", 10, "나", 15, "다", 23),
        SOCIAL_SKILLS, Map.of("가", 14, "나", 18, "다", 23)
    )),
    SURVEY4(4, "발달선별검사 4차", 10, 11, Map.of(
        GROSS_MOTOR_SKILLS, Map.of("가", 10, "나", 18, "다", 24),
        FINE_MOTOR_SKILLS, Map.of("가", 16, "나", 20, "다", 24),
        COGNITION, Map.of("가", 17, "나", 20, "다", 24),
        LANGUAGE, Map.of("가", 11, "나", 17, "다", 23),
        SOCIAL_SKILLS, Map.of("가", 12, "나", 17, "다", 23)
    ))
    ;

    private final int id;
    private final String title;
    private final int minAgeMonths;
    private final int maxAgeMonths;
    private final Map<SurveyCategory, Map<String, Integer>> scoreThresholds;

    Survey(int id, String title, int minAgeMonths, int maxAgeMonths, Map<SurveyCategory, Map<String, Integer>> scoreThresholds) {
        this.id = id;
        this.title = title;
        this.minAgeMonths = minAgeMonths;
        this.maxAgeMonths = maxAgeMonths;
        this.scoreThresholds = scoreThresholds;
    }

    public static Survey findById(int id) {
        return Stream.of(values())
            .filter(survey -> survey.getId() == id)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Survey Id입니다.: " + id));
    }
}
