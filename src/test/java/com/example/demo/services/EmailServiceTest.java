package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmailServiceTest {
    
    @Mock private JavaMailSender mailSender;
    @Mock private SimpleMailMessage message;
    @InjectMocks private EmailService service;

    @Test
    public void test_0100_send_email() {
        doAnswer((i) -> {
            final Object[] args = i.getArguments();
            assertEquals(args.length, 1);
            assertEquals(args[0], message);
            return null;
        }).when(mailSender).send(message);
        mailSender.send(message);
    }
}