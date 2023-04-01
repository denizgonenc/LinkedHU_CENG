package com.example.linkedhuceng.repository;

import com.example.linkedhuceng.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);
}
