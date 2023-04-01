package com.example.linkedhuceng.entity.relation;

import com.example.linkedhuceng.entity.Post;
import com.example.linkedhuceng.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "likes")
@Getter
@NoArgsConstructor
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // TODO OneToMany? (mustafa)
    User from;

    @ManyToOne(fetch = FetchType.LAZY)
    Post to;

    public Like(User from, Post to) {
        this.from = from;
        this.to = to;
    }
}
