package com.example.linkedhuceng.api;


import com.example.linkedhuceng.entity.EmailMessage;
import com.example.linkedhuceng.service.EmailService;
import com.example.linkedhuceng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmailController {
    private final EmailService emailService;
    private final UserService userService;
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailController(EmailService emailService, UserService userService, JavaMailSender javaMailSender){this.emailService=emailService;
        this.userService = userService;
        this.javaMailSender = javaMailSender;
    }

    @GetMapping("/mail")
    public String showMail(ModelMap modelMap){

        List<EmailMessage> emailMessagesList = emailService.getAllEmail();
        modelMap.put("emails",emailMessagesList);
        return "mail";
    }

    @PostMapping(value="/mail", params={"to","subject","content"})
    public String submitMail(@RequestParam("to") String to,
                             @RequestParam("subject") String subject,
                             @RequestParam("content") String content){
        String fullname= userService.getCurrentUser().getUsername();
        String sender=userService.getCurrentUser().getEmail();
        EmailMessage email = new EmailMessage("pınar","önemli","konuk");
        email.setMail_To(to);
        //email.setFrom(userService.getCurrentUser().getEmail());
        String mailSubject=fullname + " has sent an email";
        String mailContent= "From:" + sender + "\n";
        mailContent += "to:" + to + "\n";
        mailContent += "Subject:" +  subject + "\n";
        mailContent += "Content:" + content + "\n";

        email.setMail_Subject(mailSubject);
        email.setMail_Message(mailContent);




        emailService.sendEmail(email.getMail_To(), email.getMail_Subject(), email.getMail_Message());
        emailService.addEmail(email);


        return "mail";
    }




}
