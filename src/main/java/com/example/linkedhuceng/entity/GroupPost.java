package com.example.linkedhuceng.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
public class GroupPost extends Post{
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    public GroupPost(String content, User user, Group group) {
        super(content, user);
        this.group = group;
    }

    public GroupPost(String content, User user, File file, Group group) {
        super(content, user, file);
        this.group = group;
    }
}
