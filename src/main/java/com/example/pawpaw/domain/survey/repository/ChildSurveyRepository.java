package com.example.pawpaw.domain.survey.repository;

import com.example.pawpaw.domain.child.entity.Child;
import com.example.pawpaw.domain.survey.entity.ChildSurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChildSurveyRepository extends JpaRepository<ChildSurvey, Integer> {
    List<ChildSurvey> findByChild(Child child);

    @Query("SELECT cs FROM ChildSurvey cs WHERE cs.child.id = :childId ORDER BY cs.surveyDate DESC LIMIT 1")
    Optional<ChildSurvey> findLatestByChildId(@Param("childId") int childId);
}
