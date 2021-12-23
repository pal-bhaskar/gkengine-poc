package com.demo.dbservice.dal;

import org.springframework.data.repository.CrudRepository;

import com.demo.dbservice.model.Customer;

public interface DatabaseService extends CrudRepository<Customer, String> {

}
