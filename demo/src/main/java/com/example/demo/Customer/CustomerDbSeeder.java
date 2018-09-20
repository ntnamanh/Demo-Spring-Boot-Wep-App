package com.example.demo.Customer;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CustomerDbSeeder implements CommandLineRunner {
    private CustomerRepository customerRepository;
    public CustomerDbSeeder(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        Customer customer1 = new Customer("Bird","693 Pham Van Bach","0983909919","ntnamanhu@gmail.com");
        Customer customer2 = new Customer("Tu","123 Truong SOn","0903909929","minhtu@gmail.com");
        Customer customer3 = new Customer("Nhat","456 Pham Ngu Lao","09797090994","PhamNhat@gmail.com");
        List<Customer> newlist = Arrays.asList(customer1,customer2,customer3);
        customerRepository.deleteAll();
        for (Customer customer: newlist){
            customerRepository.save(customer);
        }
    }
}