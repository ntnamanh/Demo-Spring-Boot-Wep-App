package com.example.demo.Customer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Component
public class CustomerDbSeeder implements CommandLineRunner {
    @Autowired
    private CustomerRepository customerRepository;
    public CustomerDbSeeder(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        Calendar calendar = Calendar.getInstance();
        Customer customer1 = new Customer("Bird","693 Pham Van Bach","0983909919","ntnamanhu@gmail.com",0,calendar.getTime() ,false);
        Customer customer2 = new Customer("Tu","123 Truong SOn","0903909929","minhtu@gmail.com",0,calendar.getTime() ,false);
        Customer customer3 = new Customer("Nhat","456 Pham Ngu Lao","09797090994","PhamNhat@gmail.com",0,calendar.getTime() ,false);
        List<Customer> newlist = Arrays.asList(customer1,customer2,customer3);
        customerRepository.deleteAll();
        for (Customer customer: newlist){
            customerRepository.save(customer);
        }
    }
}

