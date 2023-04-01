package com.example.linkedhuceng.entity;

import com.example.linkedhuceng.entity.relation.Like;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
public class Post implements Comparable<Post>{
    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @OneToMany(mappedBy="to", cascade = CascadeType.ALL)
    private Set<Like> likers;

    private LocalDateTime publishDate;
    private String formattedDate;
    @Lob
    private String content;

    private int reportCount = 0;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments;

    @ManyToOne
    private User owner;

    @ManyToOne(cascade = CascadeType.ALL)
    private File file;

    @ManyToOne
    private Group group;

    public Post(String content, User user) {
        this.id = UUID.randomUUID();
        this.publishDate = LocalDateTime.now();
        this.formattedDate = getFormattedDateTime();
        this.content = content;
        this.owner = user;
    }

    public Post(String content, User user, File file) {
        this.id = UUID.randomUUID();
        this.publishDate = LocalDateTime.now();
        this.formattedDate = getFormattedDateTime();
        this.content = content;
        this.owner = user;
        this.file = file;
    }

    public Post(String content, User user, Group group) {
        this.id = UUID.randomUUID();
        this.publishDate = LocalDateTime.now();
        this.formattedDate = getFormattedDateTime();
        this.content = content;
        this.owner = user;
        this.group = group;
    }

    public Post(String content, User user, File file, Group group) {
        this.id = UUID.randomUUID();
        this.publishDate = LocalDateTime.now();
        this.formattedDate = getFormattedDateTime();
        this.content = content;
        this.owner = user;
        this.file = file;
        this.group = group;
    }

    private String getFormattedDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return this.publishDate.format(formatter);
    }

    public void report(){
        this.reportCount += 1;
    }

    public int getLikeCount(){
        return this.likers.size();
    }

    @Override
    public int compareTo(Post o) {
        return this.getPublishDate().compareTo(o.getPublishDate());
    }
}
