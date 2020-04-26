package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "customers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue()
	private Long id;
	

	@Size(min=12, max=12)
	 @Pattern(regexp="[0-9]+")
	@NotNull(message="Aadhar should be mandatery and size should be 12 numbers")
	@Column(name = "aadhar")
	
    private String aadhar; 
	
	@NotNull
    @Pattern(regexp="[A-Za-z]+")
	@NotNull(message=" Gender Should be  Mandatory")
    @Column(name = "gender")
		private String gender;
	
	@NotNull
	@Size(min = 9, max = 9)
	 @Pattern(regexp="[A-Z]{4}[0-9]{4}[A-Z]")
    @Column(name = "panCard")	
	@NotNull(message="PanCard should be mandatery that too First 4 caps then 4 numbers then 1 caps")
	private String panCard;
	
	
	
	
	
	@Pattern(regexp="[0-9]{10}")
    @Column(name = "contactNumber")
	@NotNull(message="contactNumber  is Mandatory that too 10 numbers")
	private String contactNumber;
	
	
	@Pattern(regexp="[0-9]{2}\\-[0-9]{2}\\-[0-9]{4}")
	@NotNull(message="DateOfBirth Mandatery")
    @Column(name = "dob")
	private String dob;

	
	
	
    @Size(min = 3, max = 50)
	@Pattern(regexp="[A-Za-z]+")
    @Column(name = "name")
    @NotNull(message=" Name is Mandatory")
	private String name;
      
    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "customer")
    private Address address;
    
    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "customer")
    private Account account;
	
	public Customer() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPanCard() {
		return panCard;
	}

	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Customer(Long id, String aadhar, String gender, String panCard, String contactNumber, String dob, String name,
			 Address address, Account account) {
		super();
		this.id = id;
		this.aadhar = aadhar;
		this.gender = gender;
		this.panCard = panCard;
		this.contactNumber = contactNumber;
		this.dob = dob;
		this.name = name;
		
		this.address = address;
		this.account = account;
	}
	
	
	
}