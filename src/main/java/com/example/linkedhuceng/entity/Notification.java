package com.example.linkedhuceng.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notifications")
@Getter
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID from;
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID to;
    private String type; // "1": X followed you,"2": X added comment to your post, id: X liked your post
    private String content;
    private final LocalDateTime time = LocalDateTime.now();

    public Notification(UUID from, String fromName, UUID to, String type){
        this.from = from;
        this.to = to;
        this.type = type;

        if(type.equals("1")){
            this.content = fromName + " has followed you!";
        }
        else if(type.equals("2")){
            this.content = fromName + " added a comment to your post!";
        }
        else{
            this.content = fromName + " liked your post!";
        }
    }
}
