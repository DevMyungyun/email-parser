package com.myc.email.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myc.email.service.EmailMessageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class Email {
    
    private final EmailMessageService emailMessageService;


    @PostMapping("/message-file")
    @ResponseBody
    public ResponseEntity<?> insertMessageFile() throws IOException{
        List<String> fileNameArr=emailMessageService.insertEmailMessage();
        return ResponseEntity.ok().body(fileNameArr);
    }

    @GetMapping("/info")
    @ResponseBody
    public ResponseEntity<?> getMessageInfo() {
        return ResponseEntity.ok().body(
            emailMessageService.getEmailInfo());
    }
}
