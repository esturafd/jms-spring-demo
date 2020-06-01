package com.example.demo.repositories;

import com.example.demo.entities.Payload;

import org.springframework.data.repository.CrudRepository;

public interface PayloadRepository extends CrudRepository<Payload, Long> {
    
}