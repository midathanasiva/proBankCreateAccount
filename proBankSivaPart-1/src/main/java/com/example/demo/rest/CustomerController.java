package com.example.demo.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.NotFoundException;
import com.example.demo.jpa.AccountRepository;
import com.example.demo.jpa.AddressRepository;
import com.example.demo.jpa.CustomerRepository;
import com.example.demo.model.Account;
import com.example.demo.model.Address;
import com.example.demo.model.Customer;


@RestController
//@RequestMapping("c")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private AccountRepository accountRepository;
	
	
    @GetMapping("/customers")
    public List<Customer> getAllcustomers() {
    	return customerRepository.findAll();
    }
    
    @GetMapping("/customers/{id}")
    public Customer getcustomerByID(@PathVariable Long id) {
    	Optional<Customer> optcustomer = customerRepository.findById(id);
    	if(optcustomer.isPresent()) {
    		return optcustomer.get();
    	}else {
    		throw new NotFoundException("customer not found with id " + id);
    	}
    }
    
    @PostMapping("/customers")
    public Customer createcustomer(@Valid @RequestBody Customer customer) {
        return customerRepository.save(customer);
    }
    
    @PutMapping("/customers/{id}")
    public Customer updatec(@PathVariable Long id,
                                   @Valid @RequestBody Customer customerUpdated) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(customerUpdated.getName());
                    customer.setAge(customerUpdated.getAge());
                    
                    customer.setAadhar(customerUpdated.getAadhar());
                    customer.setGender(customerUpdated.getGender());
                    customer.setPanCard(customerUpdated.getPanCard());
                    customer.setContactNumber(customerUpdated.getContactNumber());
                    customer.setDob(customerUpdated.getDob());
                   
                    return customerRepository.save(customer);
                }).orElseThrow(() -> new NotFoundException("customer not found with id " + id));
    }
    
    @DeleteMapping("/customers/{id}")
    public String deletecustomer(@PathVariable Long id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customerRepository.delete(customer);
                    return "Delete Successfully!";
                }).orElseThrow(() -> new NotFoundException("customer not found with id " + id));
    }
    
    @GetMapping("/accounts")
	public List<Account> getAllaccounts(){
		return accountRepository.findAll();
	}
	
    @GetMapping("/customers/{customerId}/accounts")
    public Account getAccountBycustomerId(@PathVariable Long customerId) {
    	
        if(!customerRepository.existsById(customerId)) {
            throw new NotFoundException("Customer not found!");
        }
    	
    	List<Account> accounts = accountRepository.findByCustomerId(customerId);
    	if(accounts.size() > 0) {
    		return accounts.get(0);
    	}else {
    		throw new NotFoundException("Account not found!");
    	}
    }
    
    @PostMapping("/customers/{customerId}/accounts")
    public Account addAccount(@PathVariable Long customerId,
                            @Valid @RequestBody Account Account) {
        return customerRepository.findById(customerId)
                .map(customer -> {
                    Account.setCustomer(customer);
                    return accountRepository.save(Account);
                }).orElseThrow(() -> new NotFoundException("Customer not found!"));
    }
 
    @PutMapping("/accounts/{accountId}")
    public Account updateAccount(@PathVariable Long accountId,
                               @Valid @RequestBody Account AccountUpdated) {
        return accountRepository.findById(accountId)
                .map(Account -> {
                    Account.setAccountNumber(AccountUpdated.getAccountNumber());
                    Account.setBalance(AccountUpdated.getBalance());
                    Account.setAccountType(AccountUpdated.getAccountType());
                    Account.setBranchId(AccountUpdated.getBranchId());
                    
                    
                    
                    return accountRepository.save(Account);
                }).orElseThrow(() -> new NotFoundException("Account not found!"));
    }
    
    @DeleteMapping("/accounts/{accountId}")
    public String deleteAccount(@PathVariable Long accountId) {
        return accountRepository.findById(accountId)
                .map(Account -> {
                	accountRepository.delete(Account);
                    return "Deleted Successfully!";
                }).orElseThrow(() -> new NotFoundException("Account not found!"));
    }
    
    
    
    

	  @PostMapping("/addresses")
	    public Address createaddress(@Valid @RequestBody Address address) {
	        return addressRepository.save(address);
	  }
	
	
	@GetMapping("/addresses")
	public List<Address> getAlladdresses(){
		return addressRepository.findAll();
	}
	
  @GetMapping("/customers/{customerId}/addresses")
  public Address getaddressBycustomerId(@PathVariable Long customerId) {
  	
      if(!customerRepository.existsById(customerId)) {
          throw new NotFoundException("Student not found!");
      }
  	
  	List<Address> address = addressRepository.findByCustomerId(customerId);
  	if(address.size() > 0) {
  		return address.get(0);
  	}else {
  		throw new NotFoundException("address not found!");
  	}
  }
  
  @PostMapping("/customers/{customerId}/addresses")
  public Address addaddress(@PathVariable Long customerId,
                          @Valid @RequestBody Address address) {
      return customerRepository.findById(customerId)
              .map(customer -> {
                  address.setCustomer(customer);
                  return addressRepository.save(address);
              }).orElseThrow(() -> new NotFoundException("Student not found!"));
  }
  
  @PutMapping("/addresses/{addressesId}")
  public Address updateaddress(@PathVariable Long addressesId,
                             @Valid @RequestBody Address addressUpdated) {
      return addressRepository.findById(addressesId)
              .map(address -> {
                  address.setCity(addressUpdated.getCity());
                  address.setAddressLine1(addressUpdated.getAddressLine1());
                  address.setAddressLine2(addressUpdated.getAddressLine2());
                  address.setCountry(addressUpdated.getCountry());
                  address.setZipCode(addressUpdated.getZipCode());
                 
                  return addressRepository.save(address);
              }).orElseThrow(() -> new NotFoundException("address not found!"));
  }
  
  @DeleteMapping("/addresses/{addressesId}")
  public String deleteaddress(@PathVariable Long addressesId) {
      return addressRepository.findById(addressesId)
              .map(address -> {
                  addressRepository.delete(address);
                  return "Deleted Successfully!";
              }).orElseThrow(() -> new NotFoundException("address not found!"));
  }
    
    
    
}