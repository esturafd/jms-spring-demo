package com.example.demo.controllers;

import com.example.demo.entities.Customer;
import com.example.demo.services.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired private CustomerService service;
    
    @GetMapping
    public Iterable<Customer> getCustomers() {
        return service.findAll();
    }

    @GetMapping
    public Customer getCustomerById(@RequestParam Long id) {
        return service.findById(id);
    }

    @GetMapping("/count")
    public long count() {
        return service.count();
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return service.create(customer);
    }

    @PutMapping
    public Customer updateCustomerr(@RequestBody Customer customer) {
        return service.update(customer);
    }

    @DeleteMapping
    public void deleteCustomer(@RequestParam Long id) {
        service.delete(id);
    }
}