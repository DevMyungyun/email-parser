package com.myc.email;

import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.myc.email.util.FileRead;

import lombok.RequiredArgsConstructor;

@SpringBootTest
public class UtilTests {
    
    @Autowired
    private FileRead fileRead;

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
}
