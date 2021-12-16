package com.demo.userservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.userservice.clients.DBServiceClient;
import com.demo.userservice.model.Customer;
import com.demo.userservice.redis.CustomerRedisRepository;

@Service
public class DatabaseService {
	
	private Logger log = LoggerFactory.getLogger(DatabaseService.class);
	
	@Autowired
	private DBServiceClient dbServiceClient;
	
	@Autowired
	private CustomerRedisRepository customerRedisRepository;

	public List<Customer> findAllCustomer() {	
		return dbServiceClient.getCustomers();
	}
	
	public Optional<Customer> findCustomerById(Long id) {
		Optional<Customer> c = customerRedisRepository.findById(id);
		if (c.isPresent()) {
			log.info("Customer is present in Redis...");
			return c;
		}
		Customer customer = dbServiceClient.getCustomerById(id);
		if (customer!= null) {
			customerRedisRepository.save(customer);
			log.info("Customer data saved into Redis...");
		}
		return Optional.of(customer);
	}

	public void save(Customer customer) {
		dbServiceClient.saveCustomer(customer);
	}
	
	public List<Customer> findAllCustomerFromRedis() {	
		List<Customer> customerList = new ArrayList<>();
		customerRedisRepository.findAll().forEach(customerList :: add);
		return customerList;
	} 

	
}
