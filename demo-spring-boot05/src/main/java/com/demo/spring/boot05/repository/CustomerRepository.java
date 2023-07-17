package com.demo.spring.boot05.repository;

import com.demo.spring.boot05.entity.Customer;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, String> {


  public Customer findByFirstName(String firstName);

  public List<Customer> findByLastName(String lastName);
}
