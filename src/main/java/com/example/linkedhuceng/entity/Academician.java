package com.example.linkedhuceng.entity;

import com.example.linkedhuceng.model.Education;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Academician extends User {
    @Id
    private UUID id;
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    private String title;
    @Transient
    private List<Education> educations;
}
