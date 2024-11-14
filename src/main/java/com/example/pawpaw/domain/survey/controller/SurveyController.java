package com.example.pawpaw.domain.survey.controller;

import com.example.pawpaw.domain.survey.dto.response.SurveyItemResponse;
import com.example.pawpaw.domain.survey.service.SurveyService;
import com.example.pawpaw.global.response.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class SurveyController {

    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @GetMapping("/surveys")
    public Response<List<SurveyItemResponse>> getSurveys() {
        List<SurveyItemResponse> surveys = surveyService.getSurveys();
        return Response.success(surveys);
    }
}
