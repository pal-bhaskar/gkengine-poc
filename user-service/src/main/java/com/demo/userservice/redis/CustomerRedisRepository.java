package com.demo.userservice.redis;

import org.springframework.data.repository.CrudRepository;

import com.demo.userservice.model.Customer;

public interface CustomerRedisRepository extends CrudRepository<Customer, String>{

}
