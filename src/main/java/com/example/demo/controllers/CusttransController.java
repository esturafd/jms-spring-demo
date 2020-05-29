package com.example.demo.controllers;

import com.example.demo.entities.Payload;
import com.example.demo.services.CusttransService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/custtrans")
public class CusttransController {
    
    @Autowired private CusttransService service;

    @PostMapping
    public void sendPayload(Payload payload) {
        service.sendToQueue(payload);
    }
}