package com.example.linkedhuceng.event.listener;


import com.example.linkedhuceng.entity.User;
import com.example.linkedhuceng.event.RegistrationCompleteEvent;
import com.example.linkedhuceng.service.EmailService;
import com.example.linkedhuceng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    private final EmailService emailService;
    private final UserService userService;

    @Autowired
    public RegistrationCompleteEventListener(EmailService emailService, UserService userService) {
        this.emailService = emailService;
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // create verification token for user with link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationToken(token, user);
        // send mail to user
        String url = event.getApplicationUrl() + "/verifyRegistration?token=" + token;
        emailService.sendEmail(user.getEmail(), "Verification", String.format("Click link to verify: %s%n", url));
    }
}
