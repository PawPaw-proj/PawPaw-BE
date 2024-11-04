package com.example.pawpaw.domain.child.entity;

import com.example.pawpaw.domain.auth.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ParentChild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_id")
    private Child child;

    public static ParentChild of(User parent, Child child) {
        ParentChild parentChild = new ParentChild();
        parentChild.parent = parent;
        parentChild.child = child;
        return parentChild;
    }
}

