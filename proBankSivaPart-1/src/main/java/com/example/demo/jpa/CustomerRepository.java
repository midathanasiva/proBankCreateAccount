package com.example.demo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Customer;



public interface CustomerRepository extends JpaRepository<Customer, Long> {
}