package com.example.pawpaw.domain.auth.repository.impl;

import com.example.pawpaw.domain.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryJpa extends JpaRepository<User, Integer> {

    Optional<User> findByAddress(String address);
}
