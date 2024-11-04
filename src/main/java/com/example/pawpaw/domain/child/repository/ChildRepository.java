package com.example.pawpaw.domain.child.repository;

import com.example.pawpaw.domain.child.entity.Child;

public interface ChildRepository {

    Child save(Child child);

    Child findById(Integer id);

    Child findByAddress(String address);

    void deleteById(Integer id);
}
