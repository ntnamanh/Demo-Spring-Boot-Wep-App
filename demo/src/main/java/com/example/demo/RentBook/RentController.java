package com.example.demo.RentBook;


import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping(value = "/Rent")
public class RentController {
    private RentRepository rentRepository;
    public RentController(RentRepository rentRepository){
        this.rentRepository = rentRepository;
    }

    @GetMapping(value = "/all")
    public List<Rent> getAll(){
        List<Rent> rentList = rentRepository.findAll();
        return  rentList;
    }

    @GetMapping(value = "/add/{bookID}&{customerID}")
    public void create_rent(@PathVariable String bookID,@PathVariable String customerID ){
        Calendar calendar = Calendar.getInstance();
        Rent rent = new Rent(customerID,bookID,calendar.getTime(),null,false);
        rentRepository.save(rent);
    }

}
