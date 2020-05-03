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



/*@Entity used to create table in database table
*@table used to give reference name for the table
*/@Entity

@Table(name = "addresses")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class Address implements Serializable{
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
	
    
    @Size(min = 3, max = 50)
	@Pattern(regexp="[A-Za-z]+")
    @Column(name = "city")
    @NotNull(message="city is Mandatory")
    private String city;
    
   
   
    @Size(min = 3, max = 100)
	@Pattern(regexp="[a-zA-Z ]*")
    @Column(name = "addressLine1")
    @NotNull(message="Address is  is Mandatory")
    private String addressLine1;
	
    
    @Size(min = 3, max = 100)
    @Pattern(regexp="[a-zA-Z ]*")
    @Column(name = "addressLine2")
    @NotNull(message="Address 2  is Mandatory")
	private String addressLine2;
	
   
    @Size(min = 3, max = 50)
    @NotNull(message="country is Mandatory")
    @Pattern(regexp="[A-Za-z]*")
    @Column(name = "country")
	private String country;
    
   
    @Pattern(regexp="[0-9]{6}")
    @NotNull(message="zipcode is Mandatory that too 6 digits Ex:531115")
    @Column(name = "zipCode")
	private String zipCode;
    
    
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
    @JoinColumn(name = "address_id", nullable = false)
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
    
    
    
    
    
    /*
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