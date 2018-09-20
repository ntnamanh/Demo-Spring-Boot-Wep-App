package com.example.demo.Customer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    private CustomerRepository customerRepository;
    public CustomerController(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @GetMapping()
    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Customer get_customer_id(@PathVariable String id){
        return customerRepository.findById(id).orElseThrow(()->new CustomerNotFoundException("Can find customer with id"));
    }
    @PostMapping(value ="/post/name&address&phone&email")
    public void add_new_customer(@PathVariable String name,@PathVariable String address,@PathVariable String phone,@PathVariable String email){
        customerRepository.save(new Customer(name,address,phone,email));
    }
    @DeleteMapping()
    public void delete_customer(@PathVariable String id){
        customerRepository.deleteById(id);
    }

}
