package com.example.demo.RentBook;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RentDbSeeder implements CommandLineRunner {
    private RentRepository rentRepository;
    public RentDbSeeder(RentRepository rentRepository){
        this.rentRepository = rentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Rent rent1 = new Rent("sadadsa","adadsadsa",true);
        this.rentRepository.deleteAll();
        rentRepository.save(rent1);
    }
}
