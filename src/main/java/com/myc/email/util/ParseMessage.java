package com.myc.email.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
public class ParseMessage {

    private final String[] column={"To", "From", "Date", "Subject", "Message-ID"};

    private HashMap<String, String> parseEmailContent(Stream<String> emailContent) {
        String[] content = emailContent.collect(Collectors.joining("\n"))
                                        .split("\r?\n|\r");
        HashMap<String, String> map=new HashMap<>();
        for(int i=0; i<column.length; i++) {
            for(int j=0; j<content.length; j++) {
                String subStringKey=content[j].substring(0, 
                                                        column[i].length());
                if (subStringKey.equals(column[i])) {
                    String[] tmp=content[j].split(":");
                    String key=column[i];
                    String value=tmp[1].trim();
                    map.put(key, value);
                    break;
                }
            }
        }
        return map;
    }

    public HashMap<String, HashMap<String, String>> getEmailMap(ReadEmailFile readEmailFile) throws IOException {
        HashMap<String, Stream<String>> map=readEmailFile.getFileMap();
        HashMap<String, HashMap<String, String>> res=new HashMap<>();
        map.entrySet().stream().forEach(entry-> {
            res.put(entry.getKey(), 
                parseEmailContent(entry.getValue()));
        });
        return res;
    }
}
