package com.myc.email.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class Email {
    
    // @GetMapping("/parse")
    // @ResponseBody
    // public ResponseEntity<?> healthCheck() {
    //     return ResponseEntity.ok().body(new HashMap<>().put("Message", "Health Check Ok!"));
    // }
}
