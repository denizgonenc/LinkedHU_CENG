package com.example.linkedhuceng.entity;


import com.example.linkedhuceng.entity.relation.Membership;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "group")
@Data
@NoArgsConstructor
public class Group {
    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @OneToOne
    private User founder;

    private String name;
    private String description;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private File gp;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private File cover;

    @OneToMany(mappedBy= "group", cascade = CascadeType.ALL)
    private List<Membership> members;

    @OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Post> groupFeed;

    private int reportCount = 0;


    public Group(User founder, String name, String description, File gp, File cover) {
        this.id = UUID.randomUUID();
        this.founder = founder;
        this.name = name;
        this.description = description;
        this.gp = gp;
        this.cover = cover;
        this.groupFeed = new ArrayList<>();
    }

    public void report(){
        this.reportCount++;
    }
}
