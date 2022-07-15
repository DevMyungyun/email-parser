package com.myc.email.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class FileRead {

    private final String emailDirectoryPath;
    private Path walkPath;

    @Autowired
    public FileRead(@Value("${file.email}") String emailDirectoryPath) {
        this.emailDirectoryPath = emailDirectoryPath;
        this.walkPath = Paths.get(emailDirectoryPath);
    }

    public void printFileName() throws IOException {
        Stream<Path> stream = Files.walk(walkPath);
        stream.filter(Files::isRegularFile).forEach(p -> System.out.println(p));
    }
    
    public List<File> getFileList() throws IOException {
        List<File> files=new ArrayList<>();
        Stream<Path> stream = Files.walk(walkPath);
        stream.filter(Files::isRegularFile).forEach(p -> {
            files.add(p.toFile());
        });
        return files;
    }
    public HashMap<String, Stream<String>> getFileMap() throws IOException {
        HashMap<String, Stream<String>> map=new HashMap<>();  
        for(final File file: getFileList()) {
            map.put(file.getName(), 
                        new BufferedReader(
                            new FileReader(file)).lines());
        }   
        return map;
    }
}
