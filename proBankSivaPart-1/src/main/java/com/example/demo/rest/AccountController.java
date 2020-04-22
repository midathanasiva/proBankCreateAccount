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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.NotFoundException;
import com.example.demo.jpa.AccountRepository;
import com.example.demo.jpa.CustomerRepository;
import com.example.demo.model.Account;


@RestController
//@RequestMapping("/api")

public class AccountController {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
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

}
