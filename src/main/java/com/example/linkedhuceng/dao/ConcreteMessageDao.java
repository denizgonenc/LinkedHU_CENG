package com.example.linkedhuceng.dao;

import com.example.linkedhuceng.entity.MessageModel;
import com.example.linkedhuceng.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ConcreteMessageDao implements MessageDao{
    private final MessageRepository messageRepository;

    @Autowired
    public ConcreteMessageDao(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Optional<MessageModel> getMessageById(UUID id) {
        return messageRepository.findById(id);
    }

    @Override
    public void addMessage(MessageModel message) {
        messageRepository.save(message);
    }

    @Override
    public List<MessageModel> getAllMessages() {
        return messageRepository.findAll();
    }
}
