package com.myc.email.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FileRead {
    
    @Value("classpath:sampleEmails/smallset/*")
    private final Resource[] resources;
    
    public void printFiles() {
        for (final Resource file : resources) {
            System.out.println(file.getFilename());
        }
    }
    
    public HashMap<String, Stream<String>> getFileMap() throws IOException {
        HashMap<String, Stream<String>> map=new HashMap<>();
        for(final Resource file: resources) {
            map.put(file.getFilename(), 
                    new BufferedReader(
                        new FileReader(file.getFile())).lines());
        }   
        return map;
    }
}
