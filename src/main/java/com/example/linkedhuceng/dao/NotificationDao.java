package com.example.linkedhuceng.dao;

import com.example.linkedhuceng.entity.Comment;
import com.example.linkedhuceng.entity.Notification;
import com.example.linkedhuceng.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class NotificationDao {
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationDao(NotificationRepository notificationRepository){
        this.notificationRepository = notificationRepository;
    }

    public void saveNotification(Notification notification){
        this.notificationRepository.save(notification);
    }

    public List<Notification> getAllNotification() {
        return notificationRepository.findAll();
    }

    public void deleteNotification(Notification notification){
        this.notificationRepository.delete(notification);
    }

    public Notification findNotificationByFromToType(UUID from, UUID to, String type){
        return this.notificationRepository.findNotificationByFromToType(from, to, type);
    }

}
