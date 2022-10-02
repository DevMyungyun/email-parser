package com.myc.email;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.myc.email.util.ParseMessage;
import com.myc.email.util.ReadEmailFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class UtilTests {
    
    @Autowired
    private ReadEmailFile readEmailFile;

    @Autowired
    private ParseMessage parseMessage;

    @Test
    void printFileName() throws IOException {
        readEmailFile.setReadMsgEmailFileStrategy();
        readEmailFile.printFileName();
    }

    @Test
    void printFileContentList() throws IOException {
        readEmailFile.setReadMsgEmailFileStrategy();
        for (final File file : readEmailFile.getFileList()) {
            log.info("========================");
            Stream<String> contents=new BufferedReader(new FileReader(file)).lines();
            contents.forEach(System.out::println);
        }
    }

    @Test
    void printFileMap() throws IOException {
        readEmailFile.setReadMsgEmailFileStrategy();
        HashMap<String, Stream<String>> map=readEmailFile.getFileMap();
        map.entrySet().stream().forEach(entry-> {
            log.info("[key] : " + entry.getKey() 
            + ", [value] : "+entry.getValue().collect(Collectors.joining("\n")));
        });
    }

    @Test
    void printEmailMap() throws IOException {
        readEmailFile.setReadMsgEmailFileStrategy();
        HashMap<String, HashMap<String, String>> map=parseMessage.getEmailMap(readEmailFile);
        map.entrySet().stream().forEach(entry-> {
            log.info("[key] : " + entry.getKey() 
            + ", [value] : "+entry.getValue());
        });
    }
}
