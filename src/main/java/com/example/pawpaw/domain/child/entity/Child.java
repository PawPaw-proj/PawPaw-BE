package com.example.pawpaw.domain.child.entity;

import com.example.pawpaw.global.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Child extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String address;
    private String name;
    private LocalDate birthDate;
    private Integer height;
    private Integer weight;
    private String profile;

    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParentChild> parents = new ArrayList<>();

    public Child update(String name, LocalDate birthDate, Integer height, Integer weight, String profile) {
        this.name = name;
        this.birthDate = birthDate;
        this.height = height;
        this.weight = weight;
        this.profile = profile;
        return this;
    }

    public int calculateAgeMonths() {
        return Period.between(birthDate, LocalDate.now()).getYears() * 12 + Period.between(birthDate, LocalDate.now()).getMonths();
    }

    public int calculateAgeDays(LocalDate date) {
        return Period.between(birthDate, date).getDays();
    }
}

