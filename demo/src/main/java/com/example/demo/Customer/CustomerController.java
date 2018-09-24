package com.example.demo.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.jws.WebParam;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    public CustomerController(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @GetMapping()
    public ModelAndView getAllCustomer(ModelAndView model){
        model.setViewName("customers");
        model.addObject("customer_datas", this.customerRepository.findAll());
        return model;
    }

    @GetMapping(value = "/search")
    public ModelAndView get_customer_id(@RequestParam(value = "search", required = true) String name, ModelAndView model){
        model.setViewName("customers");
        if(name.isEmpty()||name.trim().equals(""))
            model.addObject("customer_datas", this.customerRepository.findAll());
        else
            model.addObject("customer_datas",this.customerRepository.findAllByNameLike(name));
        return model;
    }
    @PostMapping(value ="/{customer}")
    public void add_new_customer(@RequestBody Customer newcustomer){
        customerRepository.save(newcustomer);
    }
    @DeleteMapping("/{id}")
    public void delete_customer(@PathVariable String id){
        customerRepository.deleteById(id);
    }

}
