package com.example.linkedhuceng.repository;

import com.example.linkedhuceng.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query("SELECT n FROM Notification n WHERE n.from = ?1 and n.to = ?2 and n.type = ?3")
    Notification findNotificationByFromToType(UUID from, UUID to, String type);

}
