package com.example.demo.RentBook;


import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping(value = "/rents")
public class RentController {
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

    @PostMapping(value = "/post/{cus_id}&{b_id}")
    public void create_rent(@PathVariable String cus_id,@PathVariable String b_id ){
        Calendar calendar = Calendar.getInstance();
        rentRepository.save(new Rent(cus_id,b_id,calendar.getTime(),null,false));
    }

    @DeleteMapping(value="/remove/{id}")
    public void delete_rent(@PathVariable String id){
        rentRepository.deleteById(id);
    }


}
