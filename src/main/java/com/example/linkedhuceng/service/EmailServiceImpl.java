package com.example.linkedhuceng.service;

import com.example.linkedhuceng.dao.EmailDao;
import com.example.linkedhuceng.entity.EmailMessage;
import com.example.linkedhuceng.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmailServiceImpl implements EmailService {


    private final JavaMailSender mailSender;
    private EmailDao emailDao;

    public EmailServiceImpl(JavaMailSender mailSender,EmailDao emailDao){
        this.emailDao=emailDao;
        this.mailSender=mailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom("info.maskelibesler@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        this.mailSender.send(simpleMailMessage);

    }

    @Override
    public void sendEmail(String to, String subject, String message, String from) {
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        this.mailSender.send(simpleMailMessage);
    }

    @Override
    public void addEmail(EmailMessage emailMessage) {
        emailDao.addEmail(emailMessage);

    }

    @Override
    public List<EmailMessage> getAllEmail() {
        return emailDao.getAllEmail();
    }

    @Override
    public Optional<EmailMessage> getPostById(UUID mail_id) {
        return emailDao.getEmailById(mail_id);
    }
}
