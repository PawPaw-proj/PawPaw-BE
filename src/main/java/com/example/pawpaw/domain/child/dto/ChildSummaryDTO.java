package com.example.pawpaw.domain.child.dto;

import com.example.pawpaw.domain.child.entity.Child;

public record ChildSummaryDTO(Integer id, String name, String profile) {

    public static ChildSummaryDTO of(Child child) {
        return new ChildSummaryDTO(
                child.getId(),
                child.getName(),
                child.getProfile()
        );
    }
}
