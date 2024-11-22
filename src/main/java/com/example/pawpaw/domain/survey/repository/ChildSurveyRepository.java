package com.example.pawpaw.domain.survey.repository;

import com.example.pawpaw.domain.child.entity.Child;
import com.example.pawpaw.domain.survey.entity.ChildSurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildSurveyRepository extends JpaRepository<ChildSurvey, Integer> {
    List<ChildSurvey> findByChild(Child child);
}
