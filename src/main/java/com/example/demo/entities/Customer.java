package com.example.demo.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer extends AuditableEntity<String> {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private BigDecimal balance;

    public Customer() {}

    public Customer(Long id, String name, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void updateBalance(BigDecimal amound) {
        this.balance = balance.add(amound);
    }

    @Override
    public String toString() {
        return String.format("Customer(%d,%s,%.2f,%s,%s,%s,%s)", 
                        getId(), 
                        getName(), 
                        getBalance(), 
                        getCreatedBy(), 
                        getCreateDate(), 
                        getLastModifiedBy(), 
                        getLastModifiedDate());
    }
}