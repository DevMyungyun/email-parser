package com.myc.email;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.myc.email.service.EmailMessageService;

@SpringBootTest
public class SerivceTests {
    
    @Autowired
    EmailMessageService emailService;

    @Test
    public void insertTest() throws IOException {
        List<String> result=emailService.insertEmailMessage();
        for(String fileName: result) {
            System.out.println(fileName);
        }
    }

    @Test
    public void getEmailInfo() {
        // List<EmailMessage> result=emailService.getEmailInfo();
        // for(EmailMessage emailMessage: result) {
        //     System.out.println("From: "+emailMessage.getFrom());
        //     System.out.println("To: "+emailMessage.getTo());
        //     System.out.println("Date: "+emailMessage.getDate());
        //     System.out.println("Subject: "+emailMessage.getSubject());
        //     System.out.println("Message-ID: "+emailMessage.getMessageID());
        // }
    }
}
