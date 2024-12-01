package com.example.pawpaw.domain.survey.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class SurveySection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private SurveyCategory category;

    @ElementCollection
    private List<Integer> responses;

    public SurveySection(SurveyCategory category, List<Integer> responses) {
        this.category = category;
        this.responses = responses;
    }

    public int calculateTotalScore() {
        return responses.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
