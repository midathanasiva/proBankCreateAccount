package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "accounts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 

public class Account implements Serializable{  
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue() 
	private Long id;
	
    
    @Size(min = 12, max = 12)
    @Pattern(regexp="[0-9]{12}")
	@Column(name = "accountNumber")
    @NotNull(message="Account_Number is Mandatory that too 12 digits")
	private String accountNumber; 
	 

    
    @Pattern(regexp="[A-Za-z]+")
	@Column(name = "accountType")
    @NotNull(message="accountType is Mandatory")
	private String accountType;
    
	 
	@Column(name = "balance")
	
	private Double balance;  
	 
	 
	 
	 @Size(min = 9, max = 9)
	 @Pattern(regexp="[A-Z]{3}[0-9]{6}")
	 @Column(name = "branchId")
	 @NotNull(message="BranchId is Mandatory")
	private String branchId;
	 
	 @OneToOne(fetch = FetchType.LAZY, optional = false)
	 @JoinColumn(name = "account_id", nullable = false)
	 @JsonBackReference
	 private Customer customer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Account(Long id, String accountNumber, String accountType, Double balance, String branchId,
			Customer customer) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
		this.branchId = branchId;
		this.customer = customer;
	}

	public Account() {
		super();
	}
	 
	 
	 


}