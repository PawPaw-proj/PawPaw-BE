package com.example.pawpaw.domain.child.dto;

import com.example.pawpaw.domain.child.entity.Child;
import com.example.pawpaw.global.util.DateUtils;

import java.time.LocalDate;

public record ChildDTO(Integer id, String address, String name, LocalDate birthDate,
                       double height, double weight, Integer months, String profile) {

    public static ChildDTO of(Child child) {
        return new ChildDTO(
                child.getId(),
                child.getAddress(),
                child.getName(),
                child.getBirthDate(),
                child.getHeight(),
                child.getWeight(),
                DateUtils.calculateMonthsBetween(child.getBirthDate(), LocalDate.now()),
                child.getProfile()
        );
    }
}
