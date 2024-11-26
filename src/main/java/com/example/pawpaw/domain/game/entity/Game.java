package com.example.pawpaw.domain.game.entity;

import com.example.pawpaw.domain.survey.entity.SurveyCategory;
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
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<SurveyCategory> developmentalEffects;

    private String name;
    private String imageUrl;
    private String description;
    private String steps;
    private String materials;
    private int minAgeMonths;
    private int maxAgeMonths;
}
