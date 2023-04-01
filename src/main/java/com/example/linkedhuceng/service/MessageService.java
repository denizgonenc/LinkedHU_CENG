package com.example.linkedhuceng.service;

import com.example.linkedhuceng.dao.MessageDao;
import com.example.linkedhuceng.entity.MessageModel;
import com.example.linkedhuceng.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MessageService {
    private final MessageDao messageDao;

    @Autowired
    public MessageService(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public Optional<MessageModel> getMessageById(UUID id) {
        return messageDao.getMessageById(id);
    }

    public void addMessage(MessageModel message) {
        messageDao.addMessage(message);
    }

    public List<MessageModel> getAllMessages() {
        return messageDao.getAllMessages();
    }

}
