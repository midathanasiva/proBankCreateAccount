package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AccountCreateRequest;
import com.example.demo.entity.Customer1;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.Address1Repository;
import com.example.demo.repository.Customer1Repository;

@RestController
public class AddAccountController {
	@Autowired
	private Customer1Repository customer1Repository;
	@Autowired
	private Address1Repository address1Repository;
	@Autowired
	private AccountRepository accountRepository;
	
	@PostMapping("/createCustomerAccount")
	public Customer1 createCustomerAccount(@RequestBody AccountCreateRequest request )
	{
		return customer1Repository.save(request.getCustomer1());
		
	}
	
	@GetMapping("/findAllAccounts")
	public List<Customer1> findAllAccounts()
	{
		return customer1Repository.findAll();
	}
	

}
