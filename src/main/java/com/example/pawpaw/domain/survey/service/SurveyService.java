package com.example.pawpaw.domain.survey.service;

import com.example.pawpaw.domain.auth.service.AuthService;
import com.example.pawpaw.domain.child.entity.Child;
import com.example.pawpaw.domain.child.repository.ChildRepository;
import com.example.pawpaw.domain.survey.dto.CategoryScore;
import com.example.pawpaw.domain.survey.dto.SurveyAverageData;
import com.example.pawpaw.domain.survey.dto.SurveyResult;
import com.example.pawpaw.domain.survey.dto.request.ChildSurveyRegisterRequest;
import com.example.pawpaw.domain.survey.dto.response.*;
import com.example.pawpaw.domain.survey.entity.ChildSurvey;
import com.example.pawpaw.domain.survey.entity.Survey;
import com.example.pawpaw.domain.survey.entity.SurveyCategory;
import com.example.pawpaw.domain.survey.entity.SurveySection;
import com.example.pawpaw.domain.survey.repository.ChildSurveyRepository;
import com.example.pawpaw.domain.survey.service.event.ChildSurveyRegisteredEvent;
import com.example.pawpaw.global.response.CustomException;
import com.example.pawpaw.global.response.ErrorCode;
import com.example.pawpaw.global.util.s3.S3ImageService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@AllArgsConstructor
@Transactional
@Service
public class SurveyService {

    private final ChildRepository childRepository;
    private final ChildSurveyRepository childSurveyRepository;
    private final AuthService authService;
    private final ApplicationEventPublisher eventPublisher;
    private final S3ImageService s3ImageService;

    public List<SurveyItemResponse> getSurveys() {
        return Stream.of(Survey.values())
                .map(survey -> new SurveyItemResponse(
                        survey.getId(),
                        survey.getTitle(),
                        survey.getMinAgeMonths(),
                        survey.getMaxAgeMonths()
                ))
                .toList();
    }

    public ChildSurveyRegisterResponse registerSurvey(int childId, int surveyId, ChildSurveyRegisterRequest request) {
        if (!authService.isParentOfChild(childId)) {
            throw new CustomException(ErrorCode.BAD_REQUEST_CHILD);
        }
        Child child = childRepository.findById(childId);
        List<SurveySection> sections = new ArrayList<>();
        List<Integer> surveyResponses = request.surveyResponses();
        for (int categoryIdx = 0; categoryIdx < SurveyCategory.values().length; categoryIdx++) {
            SurveyCategory category = SurveyCategory.values()[categoryIdx];
            List<Integer> categoryResponses = new ArrayList<>();
            for (int seq = 1; seq <= 8; seq++) {
                int sequence = categoryIdx * 8 + seq;
                categoryResponses.add(surveyResponses.get(sequence - 1));
            }
            SurveySection surveySection = new SurveySection(
                    category,
                    categoryResponses
            );
            sections.add(surveySection);
        }

        ChildSurvey childSurvey = childSurveyRepository.save(new ChildSurvey(child, surveyId, LocalDate.now(), child.calculateAgeMonths(), sections));
        eventPublisher.publishEvent(new ChildSurveyRegisteredEvent(surveyId, surveyResponses));
        return new ChildSurveyRegisterResponse(childSurvey.getId());
    }

    public ChildSurveyListResponse getChildSurveys(int childId) {
        if (!authService.isParentOfChild(childId)) {
            throw new CustomException(ErrorCode.BAD_REQUEST_CHILD);
        }
        Child child = childRepository.findById(childId);
        List<ChildSurvey> childSurveys = childSurveyRepository.findByChild(child);
        return ChildSurveyListResponse.from(child, childSurveys);
    }

    public ChildSurveyResponse getChildSurvey(int childSurveyId) {
        ChildSurvey childSurvey = childSurveyRepository.findById(childSurveyId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ChildSurvey Id입니다.: " + childSurveyId));

        if (!authService.isParentOfChild(childSurvey.getChild().getId())) {
            throw new CustomException(ErrorCode.BAD_REQUEST_CHILD);
        }

        return ChildSurveyResponse.from(childSurvey);
    }

    public ChildSurveySectionResponse getChildSurveySection(int childSurveyId, String categoryCode) {
        ChildSurvey childSurvey = childSurveyRepository.findById(childSurveyId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ChildSurvey Id입니다.: " + childSurveyId));
        if (!authService.isParentOfChild(childSurvey.getChild().getId())) {
            throw new CustomException(ErrorCode.BAD_REQUEST_CHILD);
        }

        SurveyCategory category = SurveyCategory.fromCode(categoryCode);
        SurveySection section = childSurvey.findSectionByCategory(category);

        return ChildSurveySectionResponse.from(section);
    }

    public SurveyResponse getSurveyQuestions(int surveyId) {
        Survey survey = Survey.findById(surveyId);
        return SurveyResponse.from(survey);
    }

    public SurveyResult getSurveyAverages(int childSurveyId) {
        try {
            ChildSurvey childSurvey = childSurveyRepository.findById(childSurveyId)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ChildSurvey Id입니다.: " + childSurveyId));

            if (!authService.isParentOfChild(childSurvey.getChild().getId())) {
                throw new CustomException(ErrorCode.BAD_REQUEST_CHILD);
            }

            List<SurveyScoreResponse> surveyScores = SurveyScoreResponse.from(childSurvey);
            List<SurveyAverageData> surveyAverages = s3ImageService.readSurveyResultsFromCsv();

            List<CategoryScore> categoryScores = surveyScores.stream()
                    .map(score -> {
                        // 평균 데이터에서 해당 카테고리 찾기
                        SurveyAverageData averageData = surveyAverages.stream()
                                .filter(avg -> avg.category().equals(score.category()))
                                .findFirst()
                                .orElseThrow(() -> new RuntimeException("No matching category in average data: " + score.category()));

                        // CategoryScore 생성
                        return new CategoryScore(score.category(), score.score(), averageData.average());
                    })
                    .toList();

            // 4. SurveyResult 생성 및 반환
            return new SurveyResult(childSurvey.getChild().getId(), categoryScores);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read survey results from csv file", e);
        }
    }
}
