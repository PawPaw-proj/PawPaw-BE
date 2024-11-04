package com.example.pawpaw.domain.auth.service;

import com.example.pawpaw.domain.auth.dto.LoginRequestDTO;
import com.example.pawpaw.domain.auth.dto.TokenResponseDTO;
import com.example.pawpaw.domain.auth.entity.User;
import com.example.pawpaw.domain.auth.repository.UserRepository;
import com.example.pawpaw.domain.auth.security.details.CustomUserDetails;
import com.example.pawpaw.domain.auth.security.token.Token;
import com.example.pawpaw.domain.auth.security.token.jwt.JwtTokenUtils;
import com.example.pawpaw.domain.auth.security.token.rft.entity.RefreshToken;
import com.example.pawpaw.domain.auth.security.token.rft.service.RefreshTokenService;
import com.example.pawpaw.domain.child.entity.Child;
import com.example.pawpaw.domain.child.repository.ParentChildRepository;
import com.example.pawpaw.global.response.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.pawpaw.global.response.ErrorCode.INVALID_REFRESH_TOKEN;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ParentChildRepository parentChildRepository;
    private final JwtTokenUtils jwtTokenUtils;
    private final RefreshTokenService refreshTokenService;

    @Override
    public User getUser() {
        String userId = SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication().getName();
        return userRepository.findById(Integer.parseInt(userId));
    }

    @Override
    public boolean isParentOfChild(Integer childId) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.childIds().contains(childId);
    }

    @Override
    public void refreshSecurityContext() {
        User user = getUser();
        List<Child> children = parentChildRepository.findChildrenByParentId(user.getId());

        // 새로운 CustomUserDetails 객체 생성
        CustomUserDetails updatedUserDetails = new CustomUserDetails(
                user.getId(),
                children.stream().map(Child::getId).toList()
        );

        // 현재 Authentication 객체를 갱신
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                updatedUserDetails,
                null,
                updatedUserDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
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
