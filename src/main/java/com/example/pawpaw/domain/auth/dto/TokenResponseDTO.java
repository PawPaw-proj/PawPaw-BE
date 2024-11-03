package com.example.pawpaw.domain.auth.dto;

import com.example.pawpaw.global.token.Token;
import lombok.Builder;

import java.util.Date;

@Builder
public record TokenResponseDTO(String type, String accessToken, String refreshToken, Date accessTokenExpiresIn,
                               Date refreshTokenExpiresIn) {

    public static TokenResponseDTO of(Token accessToken, Token refreshToken) {
        return TokenResponseDTO.builder()
                .type("Bearer")
                .accessToken(accessToken.token())
                .refreshToken(refreshToken.token())
                .accessTokenExpiresIn(accessToken.expiresIn())
                .refreshTokenExpiresIn(refreshToken.expiresIn())
                .build();
    }
}
