package com.example.pawpaw.domain.survey.service;

import com.example.pawpaw.domain.survey.entity.Survey;
import com.example.pawpaw.domain.survey.service.event.ChildSurveyRegisteredEvent;
import com.example.pawpaw.global.util.s3.S3ImageService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

@Getter
@RequiredArgsConstructor
@Service
public class ChildSurveyResultHandler {

    private final S3ImageService s3ImageService;

    @TransactionalEventListener
    public void handleChildSurveyRegisteredEvent(ChildSurveyRegisteredEvent event) {
        var survey = Survey.findById(event.surveyId());
        var csvFileName = mapSurveyToCsvFileName(survey);
        List<String> responses = event.surveyResponses().stream().map(Object::toString).toList();

        try {
            s3ImageService.addRawDataToCsvObject(csvFileName, responses);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add survey responses to csv file", e);
        }
    }

    private String mapSurveyToCsvFileName(Survey survey) {
        int minAgeMonths = survey.getMinAgeMonths();
        int maxAgeMonths = survey.getMaxAgeMonths();
        return String.format("%d-%dmonths/kdst_%d-%d_data.csv", minAgeMonths, maxAgeMonths, minAgeMonths, maxAgeMonths);
    }
}
