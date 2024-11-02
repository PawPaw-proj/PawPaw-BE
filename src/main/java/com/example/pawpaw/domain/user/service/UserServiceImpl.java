package com.example.pawpaw.domain.user.service;

import com.example.pawpaw.domain.user.dto.LoginRequestDTO;
import com.example.pawpaw.domain.user.dto.TokenResponseDTO;
import com.example.pawpaw.domain.user.entity.User;
import com.example.pawpaw.domain.user.repository.UserRepository;
import com.example.pawpaw.global.response.CustomException;
import com.example.pawpaw.global.token.Token;
import com.example.pawpaw.global.token.jwt.JwtTokenUtils;
import com.example.pawpaw.global.token.rft.entity.RefreshToken;
import com.example.pawpaw.global.token.rft.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.pawpaw.global.response.ErrorCode.INVALID_REFRESH_TOKEN;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtTokenUtils jwtTokenUtils;
    private final RefreshTokenService refreshTokenService;

    @Override
    public User getUser() {
        String userId = SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication().getName();
        return userRepository.findById(Integer.parseInt(userId));
    }

    @Transactional
    @Override
    public TokenResponseDTO login(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.optionalFindByAddress(loginRequestDTO.address())
                .orElseGet(() -> userRepository.save(User.builder()
                        .address(loginRequestDTO.address())
                        .build())
                );

        Token accessToken = jwtTokenUtils.generateAccessToken(user.getId());
        Token refreshToken = refreshTokenService.createRefreshToken(user.getId());

        return TokenResponseDTO.of(accessToken, refreshToken);
    }

    @Override
    public TokenResponseDTO refresh(String refreshToken) {

        RefreshToken findRefreshToken = refreshTokenService.findRefreshToken(refreshToken);
        Integer userId = findRefreshToken.getUserId();

        if (!userRepository.existsById(userId)) {
            throw new CustomException(INVALID_REFRESH_TOKEN);
        }

        Token newAccessToken = jwtTokenUtils.generateAccessToken(userId);

        return TokenResponseDTO.builder()
                .type("Bearer")
                .accessToken(newAccessToken.token())
                .accessTokenExpiresIn(newAccessToken.expiresIn())
                .build();
    }
}
