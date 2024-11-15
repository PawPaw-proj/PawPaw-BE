package com.example.pawpaw.domain.child.dto;

import java.time.LocalDate;

public record CreateChildDTO(String address, String name, LocalDate birthDate, Double height, Double weight) {
}
