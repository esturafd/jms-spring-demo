package com.example.demo.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Payload;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.PayloadRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CusttransQueueService {
    
    private final Logger logger = LoggerFactory.getLogger(CusttransService.class);
    @Autowired private PayloadRepository payloadRepository;
    @Autowired private CustomerRepository customerRepository;
    @Autowired private EmailService emailService;

    private Optional<Customer> validCustomer(Payload payload) {
        Optional<Customer> customer = customerRepository.findById(payload.getCustId());
        boolean existCustomer = customer.isPresent();
        boolean validDate = payload.getDate().compareTo(new Date()) <= 0;
        boolean validAmount = payload.getAmount().compareTo(BigDecimal.ZERO) != 0;
        if (existCustomer && validDate && validAmount) {
            logger.info("the costumer {} is valid", customer.get());
            return customer;
        } else {
            return Optional.empty();
        }
    }

    public void readQueue(Payload payload) {
        Optional<Customer> customer = validCustomer(payload);
        if (customer.isPresent()) {
            Customer modifiedCustomer = customer.get();
            logger.info("a new transaction of {} applied to customer {} on {}: {}", 
                    payload.getAmount(),  
                    modifiedCustomer.getName(), 
                    payload.getDate(), 
                    payload.getCustId());
            modifiedCustomer.getBalance().add(payload.getAmount());
            customerRepository.save(modifiedCustomer);
            payloadRepository.save(payload);
        } else {
            logger.info("the customer with id {} is not valid", payload.getCustId());
            emailService.send();
        }
    }
}