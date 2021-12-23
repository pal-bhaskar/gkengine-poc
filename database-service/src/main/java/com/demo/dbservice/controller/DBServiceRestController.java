package com.demo.dbservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dbservice.dal.DatabaseService;
import com.demo.dbservice.model.Customer;

@RestController
@RequestMapping("/private/v1")
public class DBServiceRestController {
	
	private Logger log = LoggerFactory.getLogger(DBServiceRestController.class);
	
	@Autowired
	private DatabaseService databaseService;

	@GetMapping("/get/customer/{id}")
	public Customer getCustomerById(@PathVariable String id) {
		
		Optional<Customer> customer = databaseService.findById(id);
		if (customer.isPresent()) {
			Customer c = customer.get();
			log.info("returning customer list ... {}",c);
			return c;
		}
		
		return null;
	}
	
	@GetMapping("/get/allcustomer")
	public List<Customer> getAllCustomer() {
		List<Customer> customerList = new ArrayList<>();
		databaseService.findAll().forEach(customerList :: add);
		log.info("returning customer list ... {}",customerList);
		return customerList;
	}
	
	@PostMapping("/add/customer")
	public Customer addContent(@RequestBody Customer customer) {
		log.info("adding customer ... {}",customer);
		Customer c = databaseService.save(customer);
		log.info("successfully added new customer...{}", c);
		return c;
	}
	
}
