package com.example.pawpaw.domain.auth.controller;

import com.example.pawpaw.domain.auth.dto.LoginRequestDTO;
import com.example.pawpaw.domain.auth.dto.TokenResponseDTO;
import com.example.pawpaw.domain.auth.service.AuthService;
import com.example.pawpaw.global.response.Response;
import com.example.pawpaw.global.util.s3.S3ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/test")
    public Response<Void> hello() {
        return Response.success();
    }

    @PostMapping("/login")
    public Response<TokenResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        TokenResponseDTO tokenResponseDTO = authService.login(loginRequestDTO);
        return Response.success(tokenResponseDTO);
    }

    @GetMapping("/refresh")
    public Response<TokenResponseDTO> refresh(@RequestHeader("refreshToken") String refreshToken) {
        TokenResponseDTO tokenResponseDTO = authService.refresh(refreshToken);
        return Response.success(tokenResponseDTO);
    }
}
