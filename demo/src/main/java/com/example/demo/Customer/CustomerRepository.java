package com.example.demo.Customer;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CustomerRepository extends MongoRepository<Customer,String> {
    Customer getById (String id);
}
