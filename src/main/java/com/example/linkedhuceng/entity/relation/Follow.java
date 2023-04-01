package com.example.linkedhuceng.entity.relation;

import com.example.linkedhuceng.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Follow {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    User from;

    @ManyToOne(fetch = FetchType.LAZY)
    User to;

    public Follow(User from, User to) {
        this.from = from;
        this.to = to;
    }
}
