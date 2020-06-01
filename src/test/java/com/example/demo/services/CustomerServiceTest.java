package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.demo.entities.Customer;
import com.example.demo.repositories.CustomerRepository;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerServiceTest {

    @Mock private CustomerRepository repository;
    @InjectMocks private CustomerService service;

    @Test
    public void test_0100_find_by_id_2() {
        String name = "Prueba";
        when(repository.findById(anyLong()))
                .thenReturn(Optional.of(new Customer(2l, name, new BigDecimal(32))));
        Customer foo = service.findById(2l);
        assertEquals(foo.getName(), name);
        assertEquals(foo.getBalance(), new BigDecimal(32));
    }

    @Test
    public void test_0110_find_all() {
        List<Customer> customers = Arrays.asList(
            new Customer(2l, "Nombre1", new BigDecimal(123)),
            new Customer(4l, "Nombre2", new BigDecimal(457)),
            new Customer(5l, "Nombre3", new BigDecimal(5468)),
            new Customer(7l, "Nombre4", new BigDecimal(47))
        );
        when(repository.findAll()).thenReturn(customers);
        assertEquals(service.findAll(), customers);
    }
}