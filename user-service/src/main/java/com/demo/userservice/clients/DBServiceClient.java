package com.demo.userservice.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.userservice.model.Customer;

//@FeignClient(value="database-service")
@FeignClient(name = "database-service", url = "${DATABASE_SERVICE_HOST:http://localhost}:8082")
public interface DBServiceClient {

    @GetMapping(value = "/private/v1/get/allcustomer", produces = "application/json")
    List<Customer> getCustomers();

    @PostMapping(value = "/private/v1/add/customer")
    void saveCustomer(Customer customerData);
}
