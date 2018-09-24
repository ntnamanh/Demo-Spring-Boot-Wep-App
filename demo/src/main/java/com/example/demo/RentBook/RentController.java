package com.example.demo.RentBook;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView getAll(ModelAndView model){
        model.setViewName("rents");
        model.addObject("rent_datas", this.rentRepository.findAll());
        return model;
    }

    @GetMapping("/return/{value}")
    public ModelAndView find_by_return(ModelAndView model, @PathVariable String value){
            Boolean check;
            model.setViewName("rents");
            if(value.equals("returned"))
                check=true;
            else
                check=false;
            model.addObject("rent_datas",this.rentRepository.findAllBy_returnIs(check));
            return model;
    }

    @GetMapping(value = "/{id}")
    public Rent find_rent_by_id(@PathVariable String id){
        return this.rentRepository.findById(id).orElseThrow(()-> new RentNotFoundException("Can not find the Rent with this id"));
    }

    @PostMapping(value = "/{rent}")
    public void create_rent(@RequestBody Rent rent ){
        this.rentRepository.save(rent);
    }

    @DeleteMapping(value="/{id}")
    public void delete_rent(@PathVariable String id){
        this.rentRepository.deleteById(id);
    }


}
