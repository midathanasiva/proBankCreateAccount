package com.example.demo.dto;

import com.example.demo.entity.Customer1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class AccountCreateRequest {
	private Customer1 customer1;

	public Customer1 getCustomer1() {
		return customer1;
	}

	public void setCustomer1(Customer1 customer1) {
		this.customer1 = customer1;
	} 

}
