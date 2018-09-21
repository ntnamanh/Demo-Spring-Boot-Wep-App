package com.example.demo.RentBook;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping(value = "/rents")
public class RentController {
    @Autowired
    private RentRepository rentRepository;
    public RentController(RentRepository rentRepository){
        this.rentRepository = rentRepository;
    }

    @GetMapping()
    public List<Rent> getAll(){
        return  rentRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Rent find_rent_by_id(@PathVariable String id){
        return rentRepository.findById(id).orElseThrow(()-> new RentNotFoundException("Can not find the Rent with this id"));
    }

    @PostMapping(value = "/{rent}")
    public void create_rent(@RequestBody Rent rent ){
        rentRepository.save(rent);
    }

    @DeleteMapping(value="/{id}")
    public void delete_rent(@PathVariable String id){
        rentRepository.deleteById(id);
    }


}
