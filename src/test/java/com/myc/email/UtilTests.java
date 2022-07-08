package com.myc.email;

import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.myc.email.util.FileRead;
import com.myc.email.util.ParseMessage;

@SpringBootTest
public class UtilTests {
    
    @Autowired
    private FileRead fileRead;

    @Autowired
    private ParseMessage parseMessage;

    @Test
    void printFileList() {
        fileRead.printFiles();
    }

    @Test
    void printFileMap() throws IOException {
        HashMap<String, Stream<String>> map=fileRead.getFileMap();
        map.entrySet().stream().forEach(entry-> {
            System.out.println("[key] : " + entry.getKey() 
            + ", [value] : "+entry.getValue().collect(Collectors.joining("\n")));
        });
    }

    @Test
    void printEmailMap() throws IOException {
        HashMap<String, HashMap<String, String>> map=parseMessage.getEmailMap();
        map.entrySet().stream().forEach(entry-> {
            System.out.println("[key] : " + entry.getKey() 
            + ", [value] : "+entry.getValue());
        });
    }
}
