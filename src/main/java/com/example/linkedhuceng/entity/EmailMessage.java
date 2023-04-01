package com.example.linkedhuceng.entity;
// TODO make this an entity + add lombok


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class EmailMessage {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID mail_id;
    private String mail_To;
    private String mail_Subject;
    private String mail_Message;

    public EmailMessage(String to, String subject, String message) {
        this.mail_id = UUID.randomUUID();
        this.mail_To = to;
        this.mail_Subject =subject;
        this.mail_Message =message;
    }

    public String getMail_To() {
        return mail_To;
    }

    public void setMail_To(String to) {
        this.mail_To = to;
    }

    public String getMail_Subject() {
        return mail_Subject;
    }

    public void setMail_Subject(String subject) {
        this.mail_Subject = subject;
    }

    public String getMail_Message() {
        return mail_Message;
    }

    public void setMail_Message(String message) {
        this.mail_Message = message;
    }
}
