package com.example.pawpaw.domain.survey.entity;

import com.example.pawpaw.domain.survey.entity.questions.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.example.pawpaw.domain.survey.entity.SurveyCategory.*;

@Getter
@RequiredArgsConstructor
public enum Survey {
    SURVEY1(1, "발달선별검사 1차", 4, 5, new LinkedHashMap<>() {{
        put(GROSS_MOTOR_SKILLS, new LinkedHashMap<>() {{
            put("가", 9);
            put("나", 14);
            put("다", 20);
        }});
        put(FINE_MOTOR_SKILLS, new LinkedHashMap<>() {{
            put("가", 11);
            put("나", 16);
            put("다", 24);
        }});
        put(COGNITION, new LinkedHashMap<>() {{
            put("가", 10);
            put("나", 14);
            put("다", 20);
        }});
        put(LANGUAGE, new LinkedHashMap<>() {{
            put("가", 9);
            put("나", 16);
            put("다", 23);
        }});
        put(SOCIAL_SKILLS, new LinkedHashMap<>() {{
            put("가", 11);
            put("나", 15);
            put("다", 22);
        }});
    }}, new LinkedHashMap<>() {{
        put(GROSS_MOTOR_SKILLS, Step1SurveyQuestion.GROSS_MOTOR_SKILLS.getQuestions());
        put(FINE_MOTOR_SKILLS, Step1SurveyQuestion.FINE_MOTOR_SKILLS.getQuestions());
        put(COGNITION, Step1SurveyQuestion.COGNITION.getQuestions());
        put(LANGUAGE, Step1SurveyQuestion.LANGUAGE.getQuestions());
        put(SOCIAL_SKILLS, Step1SurveyQuestion.SOCIAL_SKILLS.getQuestions());
    }}),
    SURVEY2(2, "발달선별검사 2차", 6, 7, new LinkedHashMap<>() {{
        put(GROSS_MOTOR_SKILLS, new LinkedHashMap<>() {{
            put("가", 7);
            put("나", 12);
            put("다", 21);
        }});
        put(FINE_MOTOR_SKILLS, new LinkedHashMap<>() {{
            put("가", 14);
            put("나", 19);
            put("다", 24);
        }});
        put(COGNITION, new LinkedHashMap<>() {{
            put("가", 13);
            put("나", 17);
            put("다", 23);
        }});
        put(LANGUAGE, new LinkedHashMap<>() {{
            put("가", 11);
            put("나", 16);
            put("다", 24);
        }});
        put(SOCIAL_SKILLS, new LinkedHashMap<>() {{
            put("가", 13);
            put("나", 15);
            put("다", 22);
        }});
    }}, new LinkedHashMap<>() {{
        put(GROSS_MOTOR_SKILLS, Step2SurveyQuestion.GROSS_MOTOR_SKILLS.getQuestions());
        put(FINE_MOTOR_SKILLS, Step2SurveyQuestion.FINE_MOTOR_SKILLS.getQuestions());
        put(COGNITION, Step2SurveyQuestion.COGNITION.getQuestions());
        put(LANGUAGE, Step2SurveyQuestion.LANGUAGE.getQuestions());
        put(SOCIAL_SKILLS, Step2SurveyQuestion.SOCIAL_SKILLS.getQuestions());
    }}),
//    SURVEY3(3, "발달선별검사 3차", 8, 9, new LinkedHashMap<>() {{
//        put(GROSS_MOTOR_SKILLS, new LinkedHashMap<>() {{
//            put("가", 5);
//            put("나", 15);
//            put("다", 24);
//        }});
//        put(FINE_MOTOR_SKILLS, new LinkedHashMap<>() {{
//            put("가", 16);
//            put("나", 20);
//            put("다", 24);
//        }});
//        put(COGNITION, new LinkedHashMap<>() {{
//            put("가", 15);
//            put("나", 19);
//            put("다", 24);
//        }});
//        put(LANGUAGE, new LinkedHashMap<>() {{
//            put("가", 10);
//            put("나", 15);
//            put("다", 23);
//        }});
//        put(SOCIAL_SKILLS, new LinkedHashMap<>() {{
//            put("가", 14);
//            put("나", 18);
//            put("다", 23);
//        }});
//    }}, new LinkedHashMap<>() {{
//        put(GROSS_MOTOR_SKILLS, Step3SurveyQuestion.GROSS_MOTOR_SKILLS.getQuestions());
//        put(FINE_MOTOR_SKILLS, Step3SurveyQuestion.FINE_MOTOR_SKILLS.getQuestions());
//        put(COGNITION, Step3SurveyQuestion.COGNITION.getQuestions());
//        put(LANGUAGE, Step3SurveyQuestion.LANGUAGE.getQuestions());
//        put(SOCIAL_SKILLS, Step3SurveyQuestion.SOCIAL_SKILLS.getQuestions());
//    }}),
//    SURVEY4(4, "발달선별검사 4차", 10, 11, new LinkedHashMap<>() {{
//        put(GROSS_MOTOR_SKILLS, new LinkedHashMap<>() {{
//            put("가", 10);
//            put("나", 18);
//            put("다", 24);
//        }});
//        put(FINE_MOTOR_SKILLS, new LinkedHashMap<>() {{
//            put("가", 16);
//            put("나", 20);
//            put("다", 24);
//        }});
//        put(COGNITION, new LinkedHashMap<>() {{
//            put("가", 17);
//            put("나", 20);
//            put("다", 24);
//        }});
//        put(LANGUAGE, new LinkedHashMap<>() {{
//            put("가", 11);
//            put("나", 17);
//            put("다", 23);
//        }});
//        put(SOCIAL_SKILLS, new LinkedHashMap<>() {{
//            put("가", 12);
//            put("나", 17);
//            put("다", 23);
//        }});
//    }}, new LinkedHashMap<>() {{
//        put(GROSS_MOTOR_SKILLS, Step4SurveyQuestion.GROSS_MOTOR_SKILLS.getQuestions());
//        put(FINE_MOTOR_SKILLS, Step4SurveyQuestion.FINE_MOTOR_SKILLS.getQuestions());
//        put(COGNITION, Step4SurveyQuestion.COGNITION.getQuestions());
//        put(LANGUAGE, Step4SurveyQuestion.LANGUAGE.getQuestions());
//        put(SOCIAL_SKILLS, Step4SurveyQuestion.SOCIAL_SKILLS.getQuestions());
//    }})
    ;

    private final int id;
    private final String title;
    private final int minAgeMonths;
    private final int maxAgeMonths;
    private final Map<SurveyCategory, Map<String, Integer>> scoreThresholds;
    private final Map<SurveyCategory, List<Question>> questionsByCategory;

    public static Survey findById(int id) {
        return Stream.of(values())
                .filter(survey -> survey.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 OldSurvey Id입니다.: " + id));
    }
}
