package com.example.linkedhuceng.repository;

import com.example.linkedhuceng.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
}
