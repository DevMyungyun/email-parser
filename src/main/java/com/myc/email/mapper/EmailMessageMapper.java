package com.myc.email.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import com.myc.email.domain.EmailMessage;
import com.myc.email.dto.EmailMessageDto;

@Lazy
@Component
@ComponentScan(basePackages = "com.myc.myc.email.domain")
public class EmailMessageMapper {
    
    @Bean
    public EmailMessageDto toDto(EmailMessage emailMessage) {
        EmailMessageDto dto=new EmailMessageDto();
        dto.setFileName(emailMessage.getFileName());
        dto.setFrom(emailMessage.getFrom());
        dto.setTo(emailMessage.getTo());
        dto.setDate(emailMessage.getDate());
        dto.setSubject(emailMessage.getSubject());
        dto.setMessageID(emailMessage.getMessageID());
        return dto;
    }

    @Bean
    public EmailMessage toEntity(EmailMessageDto dto) {
        return new EmailMessage(dto.getFileName(), dto.getTo(),
                                dto.getFrom(), dto.getDate(),
                                dto.getSubject(), dto.getMessageID());
    }
    
}
