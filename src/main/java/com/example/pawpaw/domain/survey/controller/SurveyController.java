package com.example.pawpaw.domain.survey.controller;

import com.example.pawpaw.domain.survey.dto.ChildSurveyListResponse;
import com.example.pawpaw.domain.survey.dto.ChildSurveyResponse;
import com.example.pawpaw.domain.survey.dto.request.ChildSurveyRegisterRequest;
import com.example.pawpaw.domain.survey.dto.response.ChildSurveyRegisterResponse;
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

    @PostMapping("/children/{childId}/surveys/{surveyId}")
    public Response<ChildSurveyRegisterResponse> addChildSurvey(
        @PathVariable int childId,
        @PathVariable int surveyId,
        @RequestBody ChildSurveyRegisterRequest request
    ) {
        ChildSurveyRegisterResponse response = surveyService.registerSurvey(childId, surveyId, request);
        return Response.success(response);
    }

    @GetMapping("/surveys")
    public Response<List<SurveyItemResponse>> getSurveys() {
        List<SurveyItemResponse> surveys = surveyService.getSurveys();
        return Response.success(surveys);
    }

    @GetMapping("/children/{childId}/surveys")
    public Response<ChildSurveyListResponse> getChildSurveys(@PathVariable int childId) {
        ChildSurveyListResponse response = surveyService.getChildSurveys(childId);
        return Response.success(response);
    }

    @GetMapping("/childSurveys/{childSurveyId}")
    public Response<ChildSurveyResponse> getChildSurvey(@PathVariable int childSurveyId) {
        ChildSurveyResponse response = surveyService.getChildSurvey(childSurveyId);
        return Response.success(response);
    }
}
