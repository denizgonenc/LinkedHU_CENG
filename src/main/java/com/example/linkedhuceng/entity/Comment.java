package com.example.linkedhuceng.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    private String content;
    private LocalDateTime publishDate;
    private String formattedDate;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    @OneToOne
    private User user;

    public Comment(Post post, User user, String content) {
        this.post = post;
        this.user = user;
        this.content = content;
        this.publishDate = LocalDateTime.now();
        this.formattedDate = getFormattedDateTime();
    }

    private String getFormattedDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return this.publishDate.format(formatter);
    }
}
