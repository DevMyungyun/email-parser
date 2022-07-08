package com.myc.email.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ParseMessage {
    
    private final FileRead fileRead;

    private HashMap<String, String> parseEmailContent(Stream<String> emailContent) {
        String[] content = emailContent.toString().split("\r?\n|\r");
        HashMap<String, String> map=new HashMap<>();
        String preKey="";
        for(int i=0; i<content.length; i++) {
            if(!content[i].contains(": ")) 
                map.put(preKey, map.get(preKey)+"\n"+content[i]);
            if(content[i]=="\n") {
                String lastContent="";
                while(i<content.length) {
                    lastContent+=content[i];
                    i++;
                }
                map.put("Content", lastContent);
                break;
            }
            String[] tmp=content[i].split(": ");
            String key=tmp[0];
            String value=tmp[1];
            map.put(key, value);
            preKey=key;
        }
        return map;
    }

    public HashMap<String, HashMap<String, String>> getEmailMap() throws IOException {
        HashMap<String, Stream<String>> map=fileRead.getFileMap();
        HashMap<String, HashMap<String, String>> res=new HashMap<>();
        map.entrySet().stream().forEach(entry-> {
            res.put(entry.getKey(), 
                parseEmailContent(entry.getValue()));
        });
        return res;
    }
}
