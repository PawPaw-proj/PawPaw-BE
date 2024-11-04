package com.example.pawpaw.domain.user.repository;

import com.example.pawpaw.domain.user.entity.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    User findById(Integer id);

    Optional<User> optionalFindByAddress(String address);

    boolean existsById(Integer id);
}
