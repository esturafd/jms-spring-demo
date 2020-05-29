package com.example.demo.controllers;

import com.example.demo.entities.Payload;
import com.example.demo.services.CusttransService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/custtrans")
public class CusttransController {
    
    @Autowired private CusttransService service;
    @Value("${demo.activemq.endpoint}") private String endpoint;

    @PostMapping
    public void sendPayload(Payload payload) {
        service.sendToQueue(endpoint, payload);
    }
}