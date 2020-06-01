package com.example.demo.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

@Configuration
public class MailConfig {
    
    @Bean
    @ConfigurationProperties(prefix = "demo.mail")
    public SimpleMailMessage getMailMessage() {
        return new SimpleMailMessage();
    }
}