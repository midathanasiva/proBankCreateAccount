package com.example.demo.rest;

import java.util.List;

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
import com.example.demo.jpa.AddressRepository;
import com.example.demo.jpa.CustomerRepository;
import com.example.demo.model.Address;

@RestController
//@RequestMapping("/api")
public class AddressController {
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
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
                .map(student -> {
                    address.setStudent(student);
                    return addressRepository.save(address);
                }).orElseThrow(() -> new NotFoundException("Student not found!"));
    }
    
    @PutMapping("/addresses/{addressesId}")
    public Address updateaddress(@PathVariable Long addressesId,
                               @Valid @RequestBody Address addressUpdated) {
        return addressRepository.findById(addressesId)
                .map(address -> {
                    address.setCity(addressUpdated.getCity());
                    address.setPhone(addressUpdated.getPhone());
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
