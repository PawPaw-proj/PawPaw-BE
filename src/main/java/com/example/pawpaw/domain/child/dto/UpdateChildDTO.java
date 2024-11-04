package com.example.pawpaw.domain.child.dto;

import java.time.LocalDate;

public record UpdateChildDTO(String name, LocalDate birthDate, Integer height, Integer weight, String profile) {
}
