package com.example.demo.services;

import java.util.NoSuchElementException;

import javax.persistence.EntityExistsException;

import com.example.demo.entities.Customer;
import com.example.demo.repositories.CustomerRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    
    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    @Autowired private CustomerRepository repository;
    
    public Iterable<Customer> findAll() {
        return repository.findAll();
    }

    public Customer findById(Long id) {
        logger.info("find by id {}", id);
        return repository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("customer %d does not exist", id)));
    }

    public Customer create(Customer customer) {
        logger.info("create customer {}", customer);
        if (customer.getId() != null && (customer.getId() <= 0 || repository.findById(customer.getId()).isPresent())) {
            throw new EntityExistsException(String.format("customer %d exist", customer.getId()));
        }
        return repository.save(customer);
    }

    public Customer update(Customer customer) {
        logger.info("update customer {}", customer);
        findById(customer.getId());
        return repository.save(customer);
    }

    public void delete(Long id) {
        logger.info("delete customer");
        findById(id);
        repository.deleteById(id);
    }

    public long count() {
        return repository.count();
    }
}