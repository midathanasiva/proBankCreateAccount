package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "addresses")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class Address implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue()

	private Long id;
	
    @NotNull
    @Size(min = 3, max = 20)
	@Pattern(regexp="[A-Za-z]+")
    @Column(name = "city")
    private String city;
    
   
    @NotNull
    @Size(min = 3, max = 60)
	@Pattern(regexp="[A-Za-z]+")
    @Column(name = "addressLine1")
    private String addressLine1;
	
    @NotNull
    @Size(min = 3, max = 60)
    @Pattern(regexp="[A-Za-z]+")
    @Column(name = "addressLine2")
	private String addressLine2;
	
    @NotNull
    @Size(min = 3, max = 20)
    @Pattern(regexp="[A-Za-z]+")
    @Column(name = "country")
	private String country;
    
    @NotNull
    @Pattern(regexp="[0-9]{6}")
    @Column(name = "zipCode")
	private String zipCode;
	
    
    
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    @JsonBackReference
    private Customer customer;



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	





	public String getAddressLine1() {
		return addressLine1;
	}



	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}



	public String getAddressLine2() {
		return addressLine2;
	}



	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public String getZipCode() {
		return zipCode;
	}



	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
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



	public Address(Long id, String city, String addressLine1, String addressLine2, String country,
			String zipCode, Customer customer) {
		super();
		this.id = id;
		this.city = city;
		
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.country = country;
		this.zipCode = zipCode;
		this.customer = customer;
	}



	public Address() {
		super();
	}
    
    
    
}