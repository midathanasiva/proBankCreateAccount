package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity


public class Address1 {
	@Id
	private String addressLine1;
	@NotEmpty
	private String addressLine2;
	@NotEmpty
	private String city;
	@NotEmpty
	private String country; 
	@NotEmpty//data members
	private String zipCode;
	

}
