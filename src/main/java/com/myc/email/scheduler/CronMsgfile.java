package com.myc.email.scheduler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.myc.email.domain.EmailMessage;
import com.myc.email.domain.EmailMessageRepository;
import com.myc.email.dto.EmailMessageDto;
import com.myc.email.mapper.EmailMessageMapper;
import com.myc.email.util.ParseMessage;
import com.myc.email.util.ReadEmailFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CronMsgfile {
    
    private final EmailMessageRepository emailMessageRepository;
    private final EmailMessageMapper emailMessageMapper;
    private final ReadEmailFile readEmailFile;
    private final ParseMessage parseMessage;
    
    @Scheduled(cron = "* */60 * * * *")
    public void updateNewMsgFiles() throws IOException {
        log.info("msg file update start........");
        readEmailFile.setReadMsgEmailFileStrategy();
        HashMap<String, HashMap<String, String>> map=parseMessage.getEmailMap(readEmailFile);
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
            log.warn("error occur during updating Outlook(.msg) datas!");
            e.printStackTrace();
        } finally {
            log.info("Successfully update Outlook(.msg) data.");
        }
    }
}
