package com.example.pawpaw.domain.user.controller;

import com.example.pawpaw.domain.user.dto.LoginRequestDTO;
import com.example.pawpaw.domain.user.dto.TokenResponseDTO;
import com.example.pawpaw.domain.user.service.UserService;
import com.example.pawpaw.global.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public Response<TokenResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        TokenResponseDTO tokenResponseDTO = userService.login(loginRequestDTO);
        return Response.success(tokenResponseDTO);
    }

    @GetMapping("/refresh")
    public Response<TokenResponseDTO> refresh(@RequestHeader("refreshToken") String refreshToken) {
        TokenResponseDTO tokenResponseDTO = userService.refresh(refreshToken);
        return Response.success(tokenResponseDTO);
    }
}
