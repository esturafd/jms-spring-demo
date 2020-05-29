package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Payload;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.PayloadRepository;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CusttransQueueServiceTest {
    
    @Mock private PayloadRepository payloadRepository;
    @Mock private CustomerRepository customerRepository;
    @Mock private EmailService emailService;
    @InjectMocks private CusttransQueueService service;

    @Test
    public void test_0100_read_valid_payload() {
        Customer customer = new Customer(2l, "Name", new BigDecimal(14));
        Payload payload = new Payload(2l, new BigDecimal(2), new Date());
        doReturn(Optional.of(customer)).when(customerRepository).findById(any());
        service.readQueue(payload);
        assertEquals(payload.getCustomer(), customer);
    }

    @Test
    public void test_0110_read_invalid_customer() {
        Payload payload = new Payload(2l, new BigDecimal(2), new Date());
        doReturn(Optional.empty()).when(customerRepository).findById(any());
        service.readQueue(payload);
        assertEquals(payload.getCustomer(), null);
    }

    @Test
    public void test_0120_read_invalid_payload_date() {
        Calendar foo = Calendar.getInstance();
        foo.setTime(new Date());
        foo.add(Calendar.DATE, 1);
        Date date = foo.getTime();
        Customer customer = new Customer(2l, "Name", new BigDecimal(14));
        Payload payload = new Payload(2l, new BigDecimal(2), date);
        doReturn(Optional.of(customer)).when(customerRepository).findById(any());
        service.readQueue(payload);
        assertEquals(payload.getCustomer(), null);
    }

    @Test
    public void test_0130_read_invalid_payload_amount() {
        Customer customer = new Customer(2l, "Name", new BigDecimal(14));
        Payload payload = new Payload(2l, new BigDecimal(0), new Date());
        doReturn(Optional.of(customer)).when(customerRepository).findById(any());
        service.readQueue(payload);
        assertEquals(payload.getCustomer(), null);
    }
}