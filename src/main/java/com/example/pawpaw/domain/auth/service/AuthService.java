package com.example.pawpaw.domain.auth.service;

import com.example.pawpaw.domain.auth.dto.LoginRequestDTO;
import com.example.pawpaw.domain.auth.dto.TokenResponseDTO;
import com.example.pawpaw.domain.user.entity.User;

public interface AuthService {
    User getUser();

    TokenResponseDTO login(LoginRequestDTO loginRequestDTO);

    TokenResponseDTO refresh(String refreshToken);
}
