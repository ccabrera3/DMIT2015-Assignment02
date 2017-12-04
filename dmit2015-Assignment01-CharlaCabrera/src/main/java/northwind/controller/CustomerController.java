package northwind.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;




import northwind.data.CustomerRepository;
import northwind.model.Customer;


@Model
public class CustomerController {

	
	@Inject
	private CustomerRepository customerRepository;
	
	private List<Customer> customers;
	
	@PostConstruct
	void init() {
		customers = customerRepository.findAll();
	}
	
	public List<Customer> getCustomers() {
		return customers;
	}
	

}


