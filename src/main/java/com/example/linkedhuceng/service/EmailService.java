package com.example.linkedhuceng.service;

import com.example.linkedhuceng.entity.EmailMessage;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmailService {
    void sendEmail(String to, String subject, String message);
    void sendEmail(String to,String subject,String message,String from);
    void addEmail(EmailMessage emailMessage);
    List<EmailMessage> getAllEmail();
    Optional<EmailMessage> getPostById(UUID mail_id);
}
