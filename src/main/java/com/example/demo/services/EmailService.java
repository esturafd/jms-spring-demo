package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    @Autowired private JavaMailSender mailSender;
    @Autowired private SimpleMailMessage message;

    @Async
    public void send() {
        mailSender.send(message);
    }
}