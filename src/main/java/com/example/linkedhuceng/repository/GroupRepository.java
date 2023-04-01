package com.example.linkedhuceng.repository;

import com.example.linkedhuceng.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GroupRepository extends JpaRepository<Group, UUID> {
}
