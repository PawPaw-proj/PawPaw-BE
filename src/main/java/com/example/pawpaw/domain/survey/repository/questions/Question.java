package com.example.pawpaw.domain.survey.repository.questions;

public record Question(
    int sequence,
    String question,
    String imageUrl
) {
}
