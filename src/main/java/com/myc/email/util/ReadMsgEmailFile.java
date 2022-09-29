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

class ReadMsgEmailFile implements ReadEmailStrategy {

    private Path walkPath;
    
    @Autowired
    public ReadMsgEmailFile(
                            String emailDirectoryPath
                            ) {
        this.walkPath = Paths.get(emailDirectoryPath);
    }

    @Override
    public void printFileName() throws IOException {
        // TODO Auto-generated method stub
        Stream<Path> stream = Files.walk(walkPath);
        stream.filter(Files::isRegularFile).forEach(
            p -> System.out.println(p));
    }

    @Override
    public List<File> getFileList() throws IOException {
        // TODO Auto-generated method stub
        List<File> files=new ArrayList<>();
        Stream<Path> stream = Files.walk(walkPath);
        stream.filter(Files::isRegularFile).forEach(p -> {
            files.add(p.toFile());
        });
        return files;
    }

    @Override
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