package com.myc.email.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
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
    @CreationTimestamp
    private LocalDateTime date;
    @Column(length = 60)
    private String subject;
    @Column(nullable = false, length = 320)
    private String messageID;
}