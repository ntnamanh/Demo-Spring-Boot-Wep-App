package com.example.demo.Customer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping(value = "/Customer")
public class CustomerController {

    private CustomerRepository customerRepository;
    public CustomerController(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @GetMapping(value = "/all")
    public List<Customer> getAllCustomer(){
        List<Customer> newlist = customerRepository.findAll();
        return newlist;
    }
}
