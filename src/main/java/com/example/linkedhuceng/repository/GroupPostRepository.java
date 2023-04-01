package com.example.linkedhuceng.repository;

import com.example.linkedhuceng.entity.GroupPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GroupPostRepository extends JpaRepository<GroupPost, UUID> {
}
