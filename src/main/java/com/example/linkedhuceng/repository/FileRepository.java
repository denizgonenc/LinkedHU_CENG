package com.example.linkedhuceng.repository;

import com.example.linkedhuceng.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FileRepository extends JpaRepository<File, UUID> {
}