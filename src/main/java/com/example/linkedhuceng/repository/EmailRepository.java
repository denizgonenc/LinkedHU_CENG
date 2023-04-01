package com.example.linkedhuceng.repository;

import com.example.linkedhuceng.entity.EmailMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<EmailMessage, UUID> {
}
