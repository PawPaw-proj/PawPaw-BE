package com.example.pawpaw.domain.survey.service;

import com.example.pawpaw.domain.survey.dto.response.SurveyItemResponse;
import com.example.pawpaw.domain.survey.repository.SurveyRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;

    public SurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    public List<SurveyItemResponse> getSurveys() {
        return surveyRepository.findAll().stream()
                .map(survey -> new SurveyItemResponse(
                        survey.getId(),
                        survey.getTitle(),
                        survey.getMinAgeMonths(),
                        survey.getMaxAgeMonths()
                ))
                .toList();
    }
}
