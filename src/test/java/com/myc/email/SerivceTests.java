package com.myc.email;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.myc.email.service.EmailMessageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class SerivceTests {
    
    @Autowired
    EmailMessageService emailService;

    @Test
    public void insertTest() throws IOException {
        List<String> result=emailService.insertEmailMessage();
        for(String fileName: result) {
            log.info(fileName);
        }
    }

    @Test
    public void getEmailInfo() {
        // List<EmailMessage> result=emailService.getEmailInfo();
        // for(EmailMessage emailMessage: result) {
        //     log.info("From: "+emailMessage.getFrom());
        //     log.info("To: "+emailMessage.getTo());
        //     log.info("Date: "+emailMessage.getDate());
        //     log.info("Subject: "+emailMessage.getSubject());
        //     log.info("Message-ID: "+emailMessage.getMessageID());
        // }
    }
}
