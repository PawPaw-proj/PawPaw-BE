package com.example.pawpaw.domain.survey.dto;

import java.util.List;

public record SurveyResult(int childId, List<CategoryScore> areas) {
}
