package com.example.linkedhuceng.repository;

import com.example.linkedhuceng.entity.relation.Follow;
import com.example.linkedhuceng.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    @Query("SELECT f FROM Follow f WHERE f.from = ?1 and f.to = ?2")
    Follow findFollowByFromAndTo(User from, User to);
}
