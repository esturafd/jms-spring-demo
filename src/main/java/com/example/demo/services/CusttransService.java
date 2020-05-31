package com.example.demo.services;

import java.util.Date;
import java.util.NoSuchElementException;

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
        if (payload.getCustId() == null) {
            throw new NoSuchElementException("no consumer reference");
        }
        if (payload.getDate() == null) {
            payload.setDate(new Date());
        }
        logger.info("send to queue payload {} to {}", payload, endpoint);
        template.convertAndSend(endpoint, payload);
    }
}