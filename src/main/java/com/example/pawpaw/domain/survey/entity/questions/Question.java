package com.example.pawpaw.domain.survey.entity.questions;

public record Question(
    int sequence,
    String question,
    String imageUrl
) {
}
