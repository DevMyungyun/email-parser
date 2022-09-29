package com.myc.email.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ReadEmailFile {
    
    private ReadEmailStrategy readEmailStrategy;

    private final String emailDirectoryPath;

    @Autowired
    public ReadEmailFile(
                        @Value("${file.email}") 
                        String emailDirectoryPath) {
        this.emailDirectoryPath = emailDirectoryPath;
    }
    
    // Email File Kinds
    // 1.  MSG - Implemented
    // 2.  EDB
    // 3.  EML
    // 4.  EMLX
    // 5.  ICS
    // 6.  MBOX
    // 7.  OFT
    // 8.  OLM
    // 9.  OST
    // 11. P7S
    // 12. PST
    // 13. RPMSG
    // 14. TNEF
    // 15. VCF
    
    public void setReadMsgEmailFileStrategy() {
        this.readEmailStrategy = new ReadMsgEmailFile(emailDirectoryPath);
    }

    // TODO Implement Later
    // public void setReadEdbEmailFileStrategy() {
    //     this.readEmailStrategy = new ReadMsgEmailFile(emailDirectoryPath);
    // }

    public void printFileName() throws IOException {
        readEmailStrategy.printFileName();
    }

    public List<File> getFileList() throws IOException {
        return readEmailStrategy.getFileList();
    }

    public HashMap<String, Stream<String>> getFileMap() throws IOException {
        return readEmailStrategy.getFileMap();
    }
}
