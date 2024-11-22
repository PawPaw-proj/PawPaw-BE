package com.example.pawpaw.domain.survey.dto.response;

import com.example.pawpaw.domain.child.entity.Child;

public record ChildInfoResponse(
    int id,
    String name,
    int ageMonths,
    String profileImageUrl
) {

    public static ChildInfoResponse from(Child child) {
        return new ChildInfoResponse(
            child.getId(),
            child.getName(),
            child.calculateAgeMonths(),
            child.getProfile()
        );
    }
}
