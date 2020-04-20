package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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



public class Customer1 {
	@Id
	@NotEmpty
	 private String aadhar; 
	@GeneratedValue
	private int id;
	
	@NotEmpty
	    private String name;
	@NotEmpty
		private String gender;
	@NotEmpty	                             //data members 
		private String panCard;
	@NotEmpty
		private String contactNumber;
	@NotEmpty
		private String dob;
	@OneToMany(targetEntity=Address1.class, cascade = CascadeType.ALL)
	@JoinColumn(name="CusClsForKey",referencedColumnName="aadhar")
	private List<Address1> address1;
	@OneToMany(targetEntity=Account.class, cascade = CascadeType.ALL)
	@JoinColumn(name="CusClsForKey",referencedColumnName="aadhar")
	private List<Account> account;
	
}
