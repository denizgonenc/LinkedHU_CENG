package com.example.linkedhuceng.repository;

import com.example.linkedhuceng.entity.MessageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<MessageModel, UUID> {
}
