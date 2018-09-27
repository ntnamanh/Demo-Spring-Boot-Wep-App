package com.example.demo.RentBook;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class RentDbSeeder implements CommandLineRunner {
    private RentRepository rentRepository;
    public RentDbSeeder(RentRepository rentRepository){
        this.rentRepository = rentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        this.rentRepository.deleteAll();

    }
}
