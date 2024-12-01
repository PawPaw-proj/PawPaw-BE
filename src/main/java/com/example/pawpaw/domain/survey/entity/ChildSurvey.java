package com.example.pawpaw.domain.survey.entity;

import com.example.pawpaw.domain.child.entity.Child;
import com.example.pawpaw.domain.survey.dto.CategoryScore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class ChildSurvey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_id")
    private Child child;

    @Column(nullable = false)
    private int surveyId;

    @Column(nullable = false)
    private LocalDate surveyDate;

    @Column(nullable = false)
    private int surveyAgeMonths;

    @Column(nullable = false)
    private String evaluationResult = "평가 전";

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "child_survey_id")
    private List<SurveySection> surveySections = new ArrayList<>();

    public ChildSurvey(Child child, int surveyId, LocalDate surveyDate, int surveyAgeMonths, List<SurveySection> surveySections) {
        this.child = child;
        this.surveyId = surveyId;
        this.surveyDate = surveyDate;
        this.surveyAgeMonths = surveyAgeMonths;
        this.surveySections = surveySections;
    }

    public int calculateChildAgeByDays() {
        return child.calculateAgeDays(surveyDate);
    }

    public SurveySection findSectionByCategory(SurveyCategory category) {
        return surveySections.stream()
                .filter(section -> section.getCategory().equals(category))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리의 검사 내역을 찾을 수 없습니다: " + category));
    }

    public List<SurveyCategory> getSurveyCategoriesToImprove(List<CategoryScore> categoryScores) {
        return surveySections.stream()
                .map(section -> {
                    double cutoffScore = categoryScores.stream()
                            .filter(score -> SurveyCategory.from(score.category()) == section.getCategory())
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("해당 카테고리의 점수를 찾을 수 없습니다: " + section.getCategory()))
                            .averageScore();
                    double difference = cutoffScore - section.calculateTotalScore();
                    return new AbstractMap.SimpleEntry<>(section.getCategory(), difference);
                })
                .filter(entry -> entry.getValue() > 0)
                .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
                .map(Map.Entry::getKey)
                .toList();
    }
}
