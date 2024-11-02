package com.example.pawpaw.global.token.rft.repository;

import com.example.pawpaw.global.token.rft.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
