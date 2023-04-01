package com.example.linkedhuceng.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
public class Student extends User {
    @Id
    private UUID id;
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    private int studentId;
    private int yearOfStudy;
    private LocalDate startDate;
}
