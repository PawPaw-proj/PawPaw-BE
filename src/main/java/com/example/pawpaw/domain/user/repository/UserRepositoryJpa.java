package com.example.pawpaw.domain.user.repository;

import com.example.pawpaw.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryJpa extends JpaRepository<User, Integer> {
}
