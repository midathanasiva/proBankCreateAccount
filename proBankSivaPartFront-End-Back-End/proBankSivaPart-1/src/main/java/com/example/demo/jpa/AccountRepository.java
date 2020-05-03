package com.example.demo.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Account;
import com.example.demo.model.Address;


public interface AccountRepository extends JpaRepository<Account, Long> {
	/*
	 * this is repository interface for Account class here primary key is of Long
	 * type THis is a Jpa repository (using REST calls, crud operations) we will
	 * fetch data using customerId
	 */
	List<Account> findByCustomerId(Long customerId);

}
