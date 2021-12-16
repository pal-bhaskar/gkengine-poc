package com.demo.userservice.controller;

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

import com.demo.userservice.model.Customer;
import com.demo.userservice.service.DatabaseService;

@RestController
@RequestMapping("/public/v1")
public class UserSvcRestController {
	
	private Logger log = LoggerFactory.getLogger(UserSvcRestController.class);
	
	@Autowired
	private DatabaseService databaseService;
	

	@GetMapping("/apitest/{name}")
	public String getTest(@PathVariable String name) {
		log.info("Inside test api method.. {}", name);
		return "Hello ".concat(name);
	}
	
	@GetMapping("/get/allcustomer")
	public List<Customer> getAllCustomer() {
		log.info("/get/allcustomer called..");
		return databaseService.findAllCustomer();
	}
	
	@PostMapping("/add/customer")
	public void addContent(@RequestBody Customer customer) {
		databaseService.save(customer);
		log.info("customer data saved successfully..");
	}
	
	@GetMapping("/get/customer/{id}")
	public Customer getCustomerById(@PathVariable Long id) {
		
		Optional<Customer> customer = databaseService.findCustomerById(id);
		if (customer.isPresent()) {
			Customer c = customer.get();
			log.info("returning customer list ... {}",c);
			return c;
		}		
		return null;
	}
}
