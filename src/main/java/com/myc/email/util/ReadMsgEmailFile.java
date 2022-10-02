package com.myc.email.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class ReadMsgEmailFile implements ReadEmailStrategy {

    private Path walkPath;
    private final String fileExtention = "msg";
    
    @Autowired
    public ReadMsgEmailFile(
                            String emailDirectoryPath
                            ) {
        this.walkPath = Paths.get(emailDirectoryPath);
    }

    @Override
    public void printFileName() throws IOException {
        Stream<Path> fileStream = Files.walk(walkPath);
        log.info("file name >>>>>>>>>>>");
        fileStream.filter(Files::isRegularFile)
                    .filter(path -> {
                        boolean check=false;
                        try {
                            check = this.checkModifiedFile(path);  
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return check;
                    }) 
                    .map(path -> path.toFile())
                    .filter(file -> file.getName().indexOf('.') != -1)
                    .filter(file -> this.filterFilenameExtention(file))
                    .collect(Collectors.toList())
                    .forEach(f -> log.info(f.toString()));
    }

    @Override
    public List<File> getFileList() throws IOException {
        Stream<Path> stream = Files.walk(walkPath);
        List<File> files = stream.filter(Files::isRegularFile)
                                .map(path -> path.toFile())
                                .filter(file -> file.getName().indexOf('.') != -1)
                                .filter(file -> this.filterFilenameExtention(file))
                                .collect(Collectors.toList());
        return files;
    }

    
    @Override
    public boolean filterFilenameExtention(File file) {
        return file.getName()
        .substring(file.getName().indexOf('.')+1)
                                .equals(fileExtention);
    }

    @Override
    public boolean checkModifiedFile(Path path) throws IOException {
        FileTime lastModifiedTime = Files.getLastModifiedTime(path);
        return lastModifiedTime.toInstant().isAfter(LocalDateTime.now()
                                                        // check every 1 minutes
                                                        .minusMinutes(1)
                                                        .atZone(ZoneId.systemDefault())
                                                        .toInstant());
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