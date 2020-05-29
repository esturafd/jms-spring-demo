package com.example.demo.controllers;

import com.example.demo.entities.Payload;
import com.example.demo.services.CusttransQueueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Controller;

@Controller
public class CusttransQueueController {
    
    @Autowired private CusttransQueueService service;

    @JmsListener(destination = "${demo.activemq.endpoint}")
    public void readQueue(Payload payload) {
        service.readQueue(payload);
    }
}