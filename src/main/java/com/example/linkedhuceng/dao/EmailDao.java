package com.example.linkedhuceng.dao;


import com.example.linkedhuceng.entity.EmailMessage;
import com.example.linkedhuceng.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class EmailDao {
    private final EmailRepository emailRepository;

    @Autowired
    public EmailDao(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public void addEmail(EmailMessage emailMessage) {
        emailRepository.save(emailMessage);
    }

    public List<EmailMessage> getAllEmail(){
        return emailRepository.findAll();
    }
    public Optional<EmailMessage> getEmailById(UUID mail_id) {
        return emailRepository.findById( mail_id);
    }
}
