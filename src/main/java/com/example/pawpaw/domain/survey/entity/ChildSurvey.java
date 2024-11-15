package com.example.pawpaw.domain.survey.entity;

import com.example.pawpaw.domain.child.entity.Child;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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
    private List<SurveySection> surveySections;

    public ChildSurvey(Child child, int surveyId, LocalDate surveyDate, int surveyAgeMonths, List<SurveySection> surveySections) {
        this.child = child;
        this.surveyId = surveyId;
        this.surveyDate = surveyDate;
        this.surveyAgeMonths = surveyAgeMonths;
    }
}
