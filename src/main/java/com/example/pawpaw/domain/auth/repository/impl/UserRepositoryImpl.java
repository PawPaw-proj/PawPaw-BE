package com.example.pawpaw.domain.user.repository.impl;

import com.example.pawpaw.domain.user.entity.User;
import com.example.pawpaw.domain.user.repository.UserRepository;
import com.example.pawpaw.global.response.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.example.pawpaw.global.response.ErrorCode.NOT_FOUND_MEMBER;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserRepositoryJpa userRepositoryJpa;

    @Override
    public User save(User user) {
        return userRepositoryJpa.save(user);
    }

    @Override
    public User findById(Integer id) {
        return userRepositoryJpa.findById(id).orElseThrow(() -> new CustomException(NOT_FOUND_MEMBER));
    }

    @Override
    public Optional<User> optionalFindByAddress(String address) {
        return userRepositoryJpa.findByAddress(address);
    }

    @Override
    public boolean existsById(Integer id) {
        return userRepositoryJpa.existsById(id);
    }
}
