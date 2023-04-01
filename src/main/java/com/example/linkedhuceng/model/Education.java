package com.example.linkedhuceng.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Education {
    private String type; // Undergrad, MS, PhD and so on
    private String department;
    private String institution;
    private LocalDate startDate;
    private LocalDate finishDate;
}
