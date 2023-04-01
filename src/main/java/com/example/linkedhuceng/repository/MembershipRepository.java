package com.example.linkedhuceng.repository;

import com.example.linkedhuceng.entity.Group;
import com.example.linkedhuceng.entity.User;
import com.example.linkedhuceng.entity.relation.Follow;
import com.example.linkedhuceng.entity.relation.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MembershipRepository extends JpaRepository<Membership, Long> {
    @Query("SELECT m FROM Membership m WHERE m.member = ?1 and m.group = ?2")
    Membership findMembership(User member, Group group);
}
