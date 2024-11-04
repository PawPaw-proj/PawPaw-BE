package com.example.pawpaw.domain.child.repository.impl;

import com.example.pawpaw.domain.child.entity.Child;
import com.example.pawpaw.domain.child.entity.ParentChild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParentChildRepositoryJpa extends JpaRepository<ParentChild, Integer> {

    @Query("SELECT pc.child FROM ParentChild pc WHERE pc.parent.id = :parentId")
    List<Child> findChildrenByParentId(@Param("parentId") Integer parentId);
}
