package com.myc.email.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
COLUMN
- To
- From
- Date
- Subject
- Message-ID
 */

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailMessage {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length=260)
    private String fileName;
    @Column(nullable = false, name = "emailTo", length = 320)
    private String to;
    @Column(nullable = false, name = "emailFrom", length = 320)
    private String from;
    @Column(length = 30)
    private String date;
    @Column(length = 200)
    private String subject;
    @Column(nullable = false, length = 320)
    private String messageID;

    @Builder
    public EmailMessage(String fileName, String to, String from, String date, String subject, String messageID) {
        this.fileName = fileName;
        this.to = to;
        this.from = from;
        this.date = date;
        this.subject = subject;
        this.messageID = messageID;
    }
}
