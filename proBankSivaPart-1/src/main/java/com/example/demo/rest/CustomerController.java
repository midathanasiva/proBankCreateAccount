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
import com.example.demo.jpa.CustomerRepository;
import com.example.demo.model.Customer;


@RestController
//@RequestMapping("c")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
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
}