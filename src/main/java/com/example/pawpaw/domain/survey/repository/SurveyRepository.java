package com.example.pawpaw.domain.survey.repository;

import com.example.pawpaw.domain.survey.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

}
