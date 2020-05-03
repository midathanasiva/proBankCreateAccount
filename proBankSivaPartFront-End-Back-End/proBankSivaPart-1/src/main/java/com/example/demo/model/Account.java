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


/*@Entity used to create table in database table
*@table used to give reference name for the table
*/
@Entity
@Table(name = "accounts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 

public class Account implements Serializable{  
	private static final long serialVersionUID = 1L;
	
	/*
	 * serialVersionUID is a unique identifier for Serializable classes . This is
	 * used during the deserialization of an object, to ensure that a loaded class
	 * is compatible with the serialized object. If no matching class is found, an
	 * InvalidClassException is thrown
	 */
	
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
	 
	/*
	 * Account class is used to store account details of a customer
	 * 
	 * @size annotation is used to mention the size or range by with the input will
	 * accept
	 * 
	 * @pattern used to make validations fo input string , accepting the input
	 * accourding to our requirement
	 * 
	 * @colon used to give the new alias name to table colon
	 * 
	 * @notNull will make sure that the input is notnull
	 */
	 
	 @Size(min = 9, max = 9)
	 @Pattern(regexp="[A-Z]{3}[0-9]{6}")
	 @Column(name = "branchId")
	 @NotNull(message="BranchId is Mandatory")
	private String branchId;
	 
	 
	/*
	 * @OneToOne is used for relationships between two tables , here this class
	 * having one-to-one relation with customer class here
	 * "fetch = FetchType.LAZY, optional = false" The entity that represents the
	 * table containing the foreign key column is called the owning side of the
	 * association. On this entity, Hibernate supports lazy loading as expected. You
	 * just have to set the fetch attribute of the @OneToOne association to
	 * FetchType.LAZY. But if you model this as a bidirectional association, you
	 * will quickly recognize that Hibernate always fetches the other end of the
	 * association eagerly. then it will wait for some time till it gets responce
	 */
	 
	 @OneToOne(fetch = FetchType.LAZY, optional = false)
	 @JoinColumn(name = "account_id", nullable = false)
	 @JsonBackReference
	 private Customer customer;
	/*
	 * @JsonManagedReference and @JsonBackReference are used to handle circular
	 * references.
	 * 
	 * @JsonManagedReference is used on a child reference of the target POJO.
	 * 
	 * @JsonBackReference is used in the corresponding child class. It is placed on the back-reference property
	 */

	public Long getId() {                             //getters and setters
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
			Customer customer) {//parametrized constructor for all data-members
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