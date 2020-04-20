package com.example.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity

public class Account {  
	@Id
	private String accountNumber; //this is a bean class with name Account
	private String accountType;
	private Double balance;
                                      //data members 
	private String branchId;
	
}
