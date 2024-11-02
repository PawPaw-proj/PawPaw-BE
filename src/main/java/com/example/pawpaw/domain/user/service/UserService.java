package com.example.pawpaw.domain.user.service;

import com.example.pawpaw.domain.user.dto.LoginRequestDTO;
import com.example.pawpaw.domain.user.dto.TokenResponseDTO;
import com.example.pawpaw.domain.user.entity.User;

public interface UserService {
    User getUser();

    TokenResponseDTO login(LoginRequestDTO loginRequestDTO);

    TokenResponseDTO refresh(String refreshToken);

}
