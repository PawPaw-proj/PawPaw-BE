package com.example.pawpaw.domain.auth.token.rft.service;


import com.example.pawpaw.domain.auth.token.Token;
import com.example.pawpaw.domain.auth.token.rft.entity.RefreshToken;
import com.example.pawpaw.domain.auth.token.rft.repository.RefreshTokenRepository;
import com.example.pawpaw.global.response.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

import static com.example.pawpaw.global.response.ErrorCode.INVALID_REFRESH_TOKEN;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final long expirationTimeInMillis = 1000 * 60 * 60 * 24 * 7; // 1주일


    public Token createRefreshToken(Integer userId) {
        String refreshToken = UUID.randomUUID().toString();

        RefreshToken tokenEntity = RefreshToken.builder()
                .refreshToken(refreshToken)
                .userId(userId)
                .build();

        refreshTokenRepository.save(tokenEntity);
        Date expiration = new Date(System.currentTimeMillis() + expirationTimeInMillis);

        return new Token(refreshToken, expiration);
    }

    @Transactional(readOnly = true)
    public RefreshToken findRefreshToken(String refreshToken) {
        return refreshTokenRepository.findById(refreshToken).
                orElseThrow(() -> new CustomException(INVALID_REFRESH_TOKEN));
    }
}
