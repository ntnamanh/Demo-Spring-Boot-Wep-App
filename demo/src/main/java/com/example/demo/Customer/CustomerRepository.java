package com.example.demo.Customer;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRepository extends MongoRepository<Customer,String> {
    List<Customer> getById (String id);
    List<Customer> findAllByNameLike(String name);
    List<Customer> findAllByNumberofrentIsGreaterThan(int number);
    List<Customer> findAllByNumberofrentIs(int number);
}
