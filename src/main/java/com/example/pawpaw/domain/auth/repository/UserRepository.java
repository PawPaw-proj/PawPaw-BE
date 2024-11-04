package com.example.pawpaw.domain.auth.repository;

import com.example.pawpaw.domain.auth.entity.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    User findById(Integer id);

    Optional<User> optionalFindByAddress(String address);

    boolean existsById(Integer id);
}
