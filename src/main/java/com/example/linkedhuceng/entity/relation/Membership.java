package com.example.linkedhuceng.entity.relation;

import com.example.linkedhuceng.entity.Group;
import com.example.linkedhuceng.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Membership {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    User member;

    @ManyToOne(fetch = FetchType.LAZY)
    Group group;

    public Membership(User member, Group group) {
        this.member = member;
        this.group = group;
    }
}
