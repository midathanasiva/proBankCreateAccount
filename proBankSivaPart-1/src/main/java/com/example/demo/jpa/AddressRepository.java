package com.example.demo.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Address;



public interface AddressRepository extends JpaRepository<Address, Long> {
	List<Address> findByCustomerId(Long customerId);	
}
