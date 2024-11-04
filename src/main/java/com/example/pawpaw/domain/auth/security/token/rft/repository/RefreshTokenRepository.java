package com.example.pawpaw.domain.auth.security.token.rft.repository;

import com.example.pawpaw.domain.auth.security.token.rft.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
