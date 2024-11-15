package com.example.pawpaw.domain.survey.service;

import com.example.pawpaw.domain.child.entity.Child;
import com.example.pawpaw.domain.child.repository.ChildRepository;
import com.example.pawpaw.domain.survey.dto.ChildSurveyListResponse;
import com.example.pawpaw.domain.survey.dto.ChildSurveyResponse;
import com.example.pawpaw.domain.survey.dto.ChildSurveySectionResponse;
import com.example.pawpaw.domain.survey.dto.request.ChildSurveyRegisterRequest;
import com.example.pawpaw.domain.survey.dto.request.SurveyCategoryResponse;
import com.example.pawpaw.domain.survey.dto.response.ChildSurveyRegisterResponse;
import com.example.pawpaw.domain.survey.dto.response.SurveyItemResponse;
import com.example.pawpaw.domain.survey.entity.ChildSurvey;
import com.example.pawpaw.domain.survey.entity.Survey;
import com.example.pawpaw.domain.survey.entity.SurveyCategory;
import com.example.pawpaw.domain.survey.entity.SurveySection;
import com.example.pawpaw.domain.survey.repository.ChildSurveyRepository;
import com.example.pawpaw.domain.survey.repository.SurveySectionRepository;
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
        Child child = childRepository.findById(childId);
        List<SurveySection> sections = new ArrayList<>();
        for (SurveyCategoryResponse data : request.data()) {
            SurveySection surveySection = surveySectionRepository.save(new SurveySection(
                SurveyCategory.from(data.category()),
                data.surveyResponses())
            );
            sections.add(surveySection);
        }
        ChildSurvey childSurvey = childSurveyRepository.save(new ChildSurvey(child, surveyId, LocalDate.now(), child.calculateAgeMonths(), sections));
        return new ChildSurveyRegisterResponse(childSurvey.getId());
    }

    public ChildSurveyListResponse getChildSurveys(int childId) {
        Child child = childRepository.findById(childId);
        List<ChildSurvey> childSurveys = childSurveyRepository.findByChild(child);
        return ChildSurveyListResponse.from(child, childSurveys);
    }

    public ChildSurveyResponse getChildSurvey(int childSurveyId) {
        ChildSurvey childSurvey = childSurveyRepository.findById(childSurveyId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ChildSurvey Id입니다.: " + childSurveyId));

        return ChildSurveyResponse.from(childSurvey);
    }

    public ChildSurveySectionResponse getChildSurveySection(int childSurveyId, String categoryCode) {
        ChildSurvey childSurvey = childSurveyRepository.findById(childSurveyId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ChildSurvey Id입니다.: " + childSurveyId));
        SurveyCategory category = SurveyCategory.fromCode(categoryCode);
        SurveySection section = childSurvey.findSectionByCategory(category);

        return ChildSurveySectionResponse.from(section);
    }
}
