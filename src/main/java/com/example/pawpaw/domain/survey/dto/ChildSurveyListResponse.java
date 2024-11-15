package com.example.pawpaw.domain.survey.dto;

import com.example.pawpaw.domain.child.entity.Child;
import com.example.pawpaw.domain.survey.entity.ChildSurvey;

import java.util.List;

public record ChildSurveyListResponse(
    ChildInfoResponse child,
    List<ChildSurveyItemResponse> surveys
) {

    public static ChildSurveyListResponse from(Child child, List<ChildSurvey> childSurveys) {
        return new ChildSurveyListResponse(
            ChildInfoResponse.from(child),
            ChildSurveyItemResponse.from(childSurveys)
        );
    }
}
