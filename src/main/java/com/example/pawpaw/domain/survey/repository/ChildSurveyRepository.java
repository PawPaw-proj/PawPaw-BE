package com.example.pawpaw.domain.survey.repository;

import com.example.pawpaw.domain.survey.entity.ChildSurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildSurveyRepository extends JpaRepository<ChildSurvey, Integer> {
}
