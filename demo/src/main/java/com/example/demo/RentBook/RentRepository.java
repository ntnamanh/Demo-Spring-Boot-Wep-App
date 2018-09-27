package com.example.demo.RentBook;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends MongoRepository<Rent,String> {
    List<Rent> findAllByReturnStatusIs(Boolean check);
    List<Rent> findAllByOrderByStartDateDesc();
    List<Rent> findAllByCustomerIdEqualsAndReturnStatusIs(String customerId,boolean rentStatus);

}
