package com.example.pawpaw.domain.child.repository.impl;

import com.example.pawpaw.domain.child.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChildRepositoryJpa extends JpaRepository<Child, Integer> {
    Optional<Child> findOptionalByAddress(String address);
}
