package com.myc.email.controller;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
public class Common {
    
    @GetMapping("/health-check")
    @ResponseBody
    public ResponseEntity<HashMap<String, String>> healthCheck() {
        HashMap<String, String> res=new HashMap<>();
        res.put("Message", "Health Check Ok!");
        return ResponseEntity.ok().body(res);
    }
}
