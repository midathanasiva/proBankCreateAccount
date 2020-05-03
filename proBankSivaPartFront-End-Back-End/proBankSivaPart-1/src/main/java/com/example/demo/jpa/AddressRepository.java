package com.example.demo.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Address;



public interface AddressRepository extends JpaRepository<Address, Long> {
	/*
	 * this is repository interface for Address class here primary key is of Long
	 * type THis is a Jpa repository (using REST calls, crud operations) we will
	 * fetch data using customerId
	 */
	List<Address> findByCustomerId(Long customerId);	
}
