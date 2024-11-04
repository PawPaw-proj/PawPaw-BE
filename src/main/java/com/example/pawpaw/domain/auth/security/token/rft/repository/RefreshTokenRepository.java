package com.example.pawpaw.domain.auth.token.rft.repository;

import com.example.pawpaw.domain.auth.token.rft.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
