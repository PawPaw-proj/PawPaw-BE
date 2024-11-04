package com.example.pawpaw.domain.child.repository.impl;

import com.example.pawpaw.domain.child.entity.Child;
import com.example.pawpaw.domain.child.entity.ParentChild;
import com.example.pawpaw.domain.child.repository.ParentChildRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ParentChildRepositoryImpl implements ParentChildRepository {

    private final ParentChildRepositoryJpa parentChildRepositoryJpa;

    @Override
    public void save(ParentChild parentChild) {
        parentChildRepositoryJpa.save(parentChild);
    }

    @Override
    public List<Child> findChildrenByParentId(Integer parentId) {
        return parentChildRepositoryJpa.findChildrenByParentId(parentId);
    }
}
