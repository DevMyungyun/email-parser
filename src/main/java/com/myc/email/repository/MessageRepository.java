package com.myc.email.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myc.email.domain.EmailMessage;

public interface MessageRepository extends JpaRepository<EmailMessage, Long> {
    
}
