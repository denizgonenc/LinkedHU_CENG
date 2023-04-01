package com.example.linkedhuceng.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class File {
    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    private String name;
    private String fileType;
    @Lob
    private byte[] data;

    @OneToMany
    private List<Post> post;




    public File(String name, String fileType, byte[] data) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.fileType = fileType;
        this.data = data;
    }
}