package com.demo.dbservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dbservice.dal.DatabaseService;
import com.demo.dbservice.model.Customer;

@RestController
@RequestMapping("/private/v1")
public class DBServiceRestController {
	
	@Autowired
	private DatabaseService databaseService;

	
	@GetMapping("/get/allcustomer")
	public List<Customer> getAllCustomer() {
		List<Customer> customerList = new ArrayList<>();
		databaseService.findAll().forEach(customerList :: add);
		return customerList;
	}
	
	@PostMapping("/add/customer")
	public void addContent(@RequestBody Customer customer) {
		databaseService.save(customer);
	}
	
}
