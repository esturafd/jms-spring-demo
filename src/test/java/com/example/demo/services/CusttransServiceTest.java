package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;

import com.example.demo.entities.Payload;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jms.core.JmsTemplate;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CusttransServiceTest {
    
    @Mock private JmsTemplate template;
    @InjectMocks private CusttransService service;

    @Test
    public void test_0100_send_transaction() {
        String foo = "prueba";
        Payload payload = new Payload();
        doAnswer((i) -> {
            final Object[] args = i.getArguments();
            assertEquals(args.length, 2);
            assertEquals(args[0], foo);
            assertEquals(args[1], payload);
            return null;
        }).when(template).convertAndSend(foo, payload);
        service.sendToQueue(foo, payload);
    }
}