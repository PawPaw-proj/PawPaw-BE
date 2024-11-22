package com.example.pawpaw.domain.survey.service;

import com.example.pawpaw.domain.auth.service.AuthService;
import com.example.pawpaw.domain.child.entity.Child;
import com.example.pawpaw.domain.child.repository.ChildRepository;
import com.example.pawpaw.domain.survey.dto.request.ChildSurveyRegisterRequest;
import com.example.pawpaw.domain.survey.dto.response.*;
import com.example.pawpaw.domain.survey.entity.ChildSurvey;
import com.example.pawpaw.domain.survey.entity.Survey;
import com.example.pawpaw.domain.survey.entity.SurveyCategory;
import com.example.pawpaw.domain.survey.entity.SurveySection;
import com.example.pawpaw.domain.survey.repository.ChildSurveyRepository;
import com.example.pawpaw.domain.survey.repository.SurveySectionRepository;
import com.example.pawpaw.global.response.CustomException;
import com.example.pawpaw.global.response.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
    private final SurveySectionRepository surveySectionRepository;
    private final AuthService authService;

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
        for (int categoryIdx = 0; categoryIdx < 5; categoryIdx++) {
            SurveyCategory category = SurveyCategory.values()[categoryIdx];
            List<Integer> categoryResponses = new ArrayList<>();
            for (int seq = 1; seq <= 8; seq++) {
                int sequence = categoryIdx * 8 + seq;
                categoryResponses.add(surveyResponses.get(sequence - 1));
            }
            SurveySection surveySection = surveySectionRepository.save(new SurveySection(
                category,
                categoryResponses
            ));
            sections.add(surveySection);
        }

        ChildSurvey childSurvey = childSurveyRepository.save(new ChildSurvey(child, surveyId, LocalDate.now(), child.calculateAgeMonths(), sections));
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
}
