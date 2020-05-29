package com.example.demo.services;

import com.example.demo.entities.Payload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class CusttransService {

    private final Logger logger = LoggerFactory.getLogger(CusttransService.class);
    @Autowired private JmsTemplate template;
    
    public void sendToQueue(String endpoint, Payload payload) {
        logger.info("send to queue payload {} to {}", payload, endpoint);
        template.convertAndSend(endpoint, payload);
    }
}