package com.myc.email.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailMessageDto {
    private Long id;
    private String fileName;
    private String to;
    private String from;
    private String date;
    private String subject;
    private String messageID;
}
