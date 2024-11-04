package com.example.pawpaw.domain.child.repository;

import com.example.pawpaw.domain.child.entity.Child;
import com.example.pawpaw.domain.child.entity.ParentChild;

import java.util.List;

public interface ParentChildRepository {

    void save(ParentChild parentChild);

    List<Child> findChildrenByParentId(Integer parentId);
}
