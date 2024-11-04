package com.example.pawpaw.domain.user.entity;

import com.example.pawpaw.global.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String address;

    @OneToMany(mappedBy = "parent")
    private List<ParentChild> children = new ArrayList<>();
}

