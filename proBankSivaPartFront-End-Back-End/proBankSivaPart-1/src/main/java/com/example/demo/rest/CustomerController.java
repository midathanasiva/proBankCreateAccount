package com.example.demo.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.BankException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.jpa.AccountRepository;
import com.example.demo.jpa.AddressRepository;
import com.example.demo.jpa.CustomerRepository;
import com.example.demo.model.Account;
import com.example.demo.model.Address;
import com.example.demo.model.Customer;


@RestController
@CrossOrigin(origins = "http://localhost:4200")


/*
 * RestController annotation is used to create RESTful web services using Spring
 * MVC. Spring RestController takes care of mapping request data to the defined
 * request handler method. Once response body is generated from the handler
 * method, it converts it to JSON or XML response
 * 
 * @CrossOrigin() @CrossOrigin annotation to handle
 * Cross-Origin-Resource-Sharing (CORS). This annotation is used at class level
 * as well as method level in RESTful Web service controller. @CrossOrigin
 * annotation is used at method level with @RequestMapping annotation
 */
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	/*
	 * //helps to create object automatecally /
	 * /creating reference to Repository
	 * classes
	 */	
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private AccountRepository accountRepository;
	
	/*
	 * Mentioning Urls to get data , and
	 * 
	 * @GetMapping annotated methods handle the HTTP GET requests matched with given
	 * URI expression.
	 * @PostMapping is used to handle POST type of request method,
	 * @PutMapping is used to handle put type of request method,
	 * @PostMapping is used to handle POST type of request method,
	 * @DeleteMapping is used to handle Delete type of request method,
	 */
	
    @GetMapping("/customers")
    public List<Customer> getAllcustomers() {
    	return customerRepository.findAll();
    }
    
	/*
	 * the above bunch of code is used to retrieve data from database and send it to
	 * the request page it will fetch all the users in the bank "findALl " helps to
	 * fetch all the records from the data base table
	 */
    
    @GetMapping("/customers/{id}")
    public Customer getcustomerByID(@PathVariable Long id)  {
    	Optional<Customer> optcustomer = customerRepository.findById(id);
    	if(optcustomer.isPresent()) {
    		return optcustomer.get();
    	}else {
    		throw new NotFoundException("customer not found with id " + id);
    	}
    }
    
    
	/*
	 * we can also retrieve data using specific customer id. so that every data of
	 * all customers are not going to fetch data if there is any error , that error
	 * will handle in exceptions
	 */
    
    @PostMapping("/customers")
    public String createcustomer(@Valid @RequestBody Customer customer)  {
        customerRepository.save(customer);
        return  "Hi '" + customer.getName() + "', your Details "
        		+ "has been successfully added to DataBase,with unique Token id =' " + customer.getId()+" ',now use this Token-Id to add"
        				+ " Address and Account Details, now click on address tab";
    }
    
	/*
	 * it is used to post the data to the data base from the user.
	 * 
	 * @valid used for validation
	 * 
	 * @RequestBody and @ResponseBody annotations are used to bind the HTTP
	 * request/response body with a domain object in method parameter or return
	 * type. Behind the scenes, these annotation uses HTTP Message converters to
	 * convert the body of HTTP request/response to domain objects
	 * 
	 */
    
    
    @PutMapping("/customers/{id}")
    public Customer updatec(@PathVariable Long id,
                                   @Valid @RequestBody Customer customerUpdated) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(customerUpdated.getName());
                   
                    
                    customer.setAadhar(customerUpdated.getAadhar());
                    customer.setGender(customerUpdated.getGender());
                    customer.setPanCard(customerUpdated.getPanCard());
                    customer.setContactNumber(customerUpdated.getContactNumber());
                    customer.setDob(customerUpdated.getDob());
                   
                    return customerRepository.save(customer);
                }).orElseThrow(() -> new NotFoundException("customer not found with id " + id));
    }
    
	/*
	 * to update Data in the dataBase , when customer wants , we can do that by the
	 * above putMapping method if there is any error,that will handle with exception
	 * class here it will first update the data then only data will be saved
	 */
    
    
    @DeleteMapping("/customers/{id}")
    public String deletecustomer(@PathVariable Long id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customerRepository.delete(customer);
                    return "Delete Successfully!";
                }).orElseThrow(() -> new NotFoundException("customer not found with id " + id));
    }
    
    
    
	/*
	 * To delete data or customer Existing Account in the DataBase we use this
	 * method.
	 */
    
    
    
	/*
	 * the down methods works same as customer class methods but these are for
	 * Account class rest every thing is same
	 */ 
    
    @GetMapping("/accounts")
	public List<Account> getAllaccounts(){
		return accountRepository.findAll();
	}
    
	/* it helps to fetch the
	 *  data of all account class only */
	
    @GetMapping("/customers/{customerId}/accounts")
    public Account getAccountBycustomerId(@PathVariable Long customerId) {
    	
        if(!customerRepository.existsById(customerId)) {
            throw new NotFoundException("Customer not found!");
        }
    	
    	List<Account> accounts = accountRepository.findByCustomerId(customerId);
    	if(accounts.size() > 0) {
    		return accounts.get(0);
    	}else {
    		throw new NotFoundException("Account not found !");
    	}
    }
    
	/*
	 * the above helps to fetch data using customer Id ,it helps to retrieve data
	 * for one customer.
	 */
    
    @PostMapping("/customers/accounts/{customerId}")
    public String addAccount(@PathVariable Long customerId,
                            @Valid @RequestBody Account Account) {
        return customerRepository.findById(customerId)
                .map(customer -> {
                    Account.setCustomer(customer);
                    accountRepository.save(Account); 
                    return(  "Hi '" + customer.getName() + "', you have successfully created an account"
                      		+ ", now you are officially  one of proBanK's Customer ");
                }).orElseThrow(() -> new NotFoundException("Customer not found! with this account"));
    }
    
    
	/*
	 * the above is used to post the data to account related to customer using
	 * customer reference id
	 */
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
    
	/*
	 * this helps to update the data using account Id
	 * 
	 * the below code is used to delete the account details usin its id
	 */
    
    @DeleteMapping("/accounts/{accountId}")
    public String deleteAccount(@PathVariable Long accountId) {
        return accountRepository.findById(accountId)
                .map(Account -> {
                	accountRepository.delete(Account);
                    return "Deleted Successfully!";
                }).orElseThrow(() -> new NotFoundException("Account not found!"));
    }
    
    
    
    

	  @PostMapping("/addresses")
	    public Address createaddress(@Valid @RequestBody Address address){
	        return addressRepository.save(address);
	  }
	  
	
	
	@GetMapping("/addresses")
	public List<Address> getAlladdresses(){
		return addressRepository.findAll();
	}
	
	/*
	 * this helps to fetch the data of all address from the data Base
	 */
	
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
  
  @PostMapping("/customers/addresses/{customerId}")
  public String addaddress(@PathVariable Long customerId,
                          @Valid @RequestBody Address address) {
      return customerRepository.findById(customerId)
              .map(customer -> {
                  address.setCustomer(customer);
                  addressRepository.save(address);
                  return(  "Hi '" + customer.getName() + "', your AddressDetails has been"
          		+ "successfully added . Use this unique Token id =' " + customer.getId()+" ', to proceed last step, "
          				+ "click on accountDetailsTab");
              }).orElseThrow(() -> new NotFoundException("address not found :"));
  }
  
	/*
	 * to add the address details to the customerclass or to the valid customer ,we
	 * use this
	 */
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
              }).orElseThrow(() -> new NotFoundException("address not found with this id:!  "+addressesId));
  }
  
	/*
	 * this is to update address class using addressId
	 */
  
  @DeleteMapping("/addresses/{addressesId}")
  public String deleteaddress(@PathVariable Long addressesId) {
      return addressRepository.findById(addressesId)
              .map(address -> {
                  addressRepository.delete(address);
                  return "Deleted Successfully!";
              }).orElseThrow(() -> new NotFoundException("address not found with this id:!  "+addressesId));
  }
    
	/*
	 * above code is used to delete address for specific customer using address id;
	 */
    
}