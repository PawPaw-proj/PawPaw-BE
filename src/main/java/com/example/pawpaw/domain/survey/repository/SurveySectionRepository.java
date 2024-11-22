package com.example.pawpaw.domain.survey.repository;

import com.example.pawpaw.domain.survey.entity.SurveySection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveySectionRepository extends JpaRepository<SurveySection, Integer> {
}
