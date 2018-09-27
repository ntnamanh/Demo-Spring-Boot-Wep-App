package com.example.demo.Customer;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRepository extends MongoRepository<Customer,String> {
    List<Customer> findAllByNameLike(String name);
    List<Customer> findAllByBookRentsIsGreaterThan(int number);
    List<Customer> findAllByBookRentsIs(int number);
    List<Customer> findAllByOrderByNameAsc();
}
