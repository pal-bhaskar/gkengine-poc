package com.demo.userservice.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.demo.userservice.clients.DBServiceClient;
import com.demo.userservice.model.Customer;


@Service
public class DatabaseService {
	
	private Logger log = LoggerFactory.getLogger(DatabaseService.class);
	
	@Autowired
	private DBServiceClient dbServiceClient;
	
	public List<Customer> findAllCustomer() {	
		return dbServiceClient.getCustomers();
	}
	
	@CachePut(value="customerList", key="#customer.getId()")
	public Customer save(Customer customer) {
		customer.setId(UUID.randomUUID().toString());
		Customer c  = dbServiceClient.saveCustomer(customer);
		log.info("saved customer details- {}", c);
		return c;
	}
	
	@Cacheable(value="customerList", key="#id")
	public Customer findCustomerById(String id) {
		Customer customer = dbServiceClient.getCustomerById(id);
		log.info("Customer is present in DB.. {}", customer);
		return customer;
	}
	
	@CacheEvict(value = "customerList", key="#id")
	public void flushDataFromCache(String id) {
		log.info("removed data from cache for {}", id);
	}
	
}
