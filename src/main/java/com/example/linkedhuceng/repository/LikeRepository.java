package com.example.linkedhuceng.repository;

import com.example.linkedhuceng.entity.Post;
import com.example.linkedhuceng.entity.User;
import com.example.linkedhuceng.entity.relation.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LikeRepository extends JpaRepository<Like, Long> {
    @Query("SELECT l FROM Like l WHERE l.from = ?1 and l.to = ?2")
    Like findLikeByUserAndPost(User user, Post post);
}
