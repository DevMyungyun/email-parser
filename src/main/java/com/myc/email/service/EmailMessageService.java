package com.myc.email.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myc.email.domain.EmailMessage;
import com.myc.email.domain.EmailMessageRepository;
import com.myc.email.dto.EmailMessageDto;
import com.myc.email.mapper.EmailMessageMapper;
import com.myc.email.util.ParseMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailMessageService {
    
    private final EmailMessageRepository emailMessageRepository;
    private final EmailMessageMapper emailMessageMapper;
    private final ParseMessage parseMessage;

    @Transactional
    public List<String> insertEmailMessage() throws IOException{
        HashMap<String, HashMap<String, String>> map=parseMessage.getEmailMap();
        List<String> fileNameArr=new ArrayList<>();
        EmailMessageDto dto=new EmailMessageDto();
        try {
            map.entrySet().stream().forEach(KeyEntity-> {
                dto.setFileName(KeyEntity.getKey());
                HashMap<String, String> valueMap=KeyEntity.getValue();
                dto.setFrom(valueMap.get("From"));
                dto.setTo(valueMap.get("To"));
                dto.setDate(valueMap.get("Date"));
                dto.setSubject(valueMap.get("Subject"));
                dto.setMessageID(valueMap.get("Message-ID"));

                EmailMessage entity = emailMessageRepository.save(emailMessageMapper.toEntity(dto));
                fileNameArr.add(entity.getFileName());
            });
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileNameArr;
    }

    public List<HashMap<String, String>> getEmailInfo() {
        List<HashMap<String, String>> res=new ArrayList<>();
        List<EmailMessage> result = emailMessageRepository.findAll();
        for(EmailMessage emailMessage: result) {
            HashMap<String, String> map = new HashMap<>();
            map.put("To", emailMessage.getTo());
            map.put("From", emailMessage.getFrom());
            map.put("Date", emailMessage.getDate());
            map.put("Subject", emailMessage.getSubject());
            map.put("Message-ID", emailMessage.getMessageID());
            res.add(map);
        }
        return res;
    }
}
