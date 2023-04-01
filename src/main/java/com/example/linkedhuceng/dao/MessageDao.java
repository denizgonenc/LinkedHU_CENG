package com.example.linkedhuceng.dao;

import com.example.linkedhuceng.entity.MessageModel;
import com.example.linkedhuceng.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageDao {
    Optional<MessageModel> getMessageById(UUID id);
    void addMessage(MessageModel message);
    List<MessageModel> getAllMessages();
}
