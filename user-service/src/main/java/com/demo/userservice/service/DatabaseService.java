package com.demo.userservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.userservice.clients.DBServiceClient;
import com.demo.userservice.model.Customer;

@Service
public class DatabaseService {
	
	@Autowired
	private DBServiceClient dbServiceClient;

	public List<Customer> findAllCustomer() {	
		return dbServiceClient.getCustomers();
	}

	public void save(Customer customer) {
		dbServiceClient.saveCustomer(customer);
	}

	
}
