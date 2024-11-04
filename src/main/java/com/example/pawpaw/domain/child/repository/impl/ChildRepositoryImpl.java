package com.example.pawpaw.domain.child.repository.impl;

import com.example.pawpaw.domain.child.entity.Child;
import com.example.pawpaw.domain.child.repository.ChildRepository;
import com.example.pawpaw.global.response.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.example.pawpaw.global.response.ErrorCode.NOT_FOUND_CHILD;

@Repository
@RequiredArgsConstructor
public class ChildRepositoryImpl implements ChildRepository {

    private final ChildRepositoryJpa childRepositoryJpa;

    @Override
    public Child save(Child child) {
        return childRepositoryJpa.save(child);
    }

    @Override
    public Child findById(Integer id) {
        return childRepositoryJpa.findById(id).orElseThrow(() -> new CustomException(NOT_FOUND_CHILD));
    }

    @Override
    public Child findByAddress(String address) {
        return childRepositoryJpa.findOptionalByAddress(address).orElseThrow(() -> new CustomException(NOT_FOUND_CHILD, "존재하지 않는 자식 주소입니다."));
    }

    @Override
    public void deleteById(Integer id) {
        childRepositoryJpa.deleteById(id);
    }
}
