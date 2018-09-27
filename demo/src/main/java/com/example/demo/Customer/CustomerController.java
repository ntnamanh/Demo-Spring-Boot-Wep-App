package com.example.demo.Customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Calendar;

@Controller
@RequestMapping(value = "/customers")
public class CustomerController {

    private CustomerRepository customerRepository;
    public CustomerController(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @GetMapping()
    public ModelAndView getAllCustomer(ModelAndView model) {
        model.setViewName("customers");
        model.addObject("customerDatas", this.customerRepository.findAllByOrderByNameAsc());
        return model;
    }

    @GetMapping("/search")
    public ModelAndView getCustomerById(@RequestParam(value = "search", required = true) String name, ModelAndView model) {
        model.setViewName("customers");
        if(name.isEmpty()||name.trim().equals("")) {
            model.addObject("customerDatas", this.customerRepository.findAll());
        } else {
            model.addObject("customerDatas", this.customerRepository.findAllByNameLike(name));
        }
        return model;
    }
    @GetMapping("/return/{value}")
    public ModelAndView getCustomerByStatus(ModelAndView model,@PathVariable String value) {
        model.setViewName("customers");
        switch (value) {
            case "is_renting":
                model.addObject("customerDatas", this.customerRepository.findAllByBookRentsIsGreaterThan(0));
                break;
            case "not_renting":
                model.addObject("customerDatas", this.customerRepository.findAllByBookRentsIs(0));
                break;
            default:
                model.setViewName("errorhandler");
                break;
        }
        return model;
    }

    @PostMapping
    public ModelAndView addCustomer(@ModelAttribute(value = "newCustomer")  Customer customer,ModelAndView model) {
        Calendar cal = Calendar.getInstance();
        Customer newCustomer = new Customer(customer.getName(),customer.getAddress(),customer.getPhone(),customer.getEmail(),0,cal.getTime(),false);
        this.customerRepository.save(newCustomer);
        model.setViewName("customers");
        model.addObject("customerDatas",this.customerRepository.findAllByOrderByNameAsc());
        return model;
    }


    @PutMapping
    public ModelAndView editCustomer(@ModelAttribute(value = "editCustomer") Customer editCustomer,@RequestParam(value = "id") String id,ModelAndView model) {
        this.customerRepository.findById(id).ifPresent(customer->{
            customer.setName(editCustomer.getName());
            customer.setAddress(editCustomer.getAddress());
            customer.setEmail(editCustomer.getEmail());
            customer.setPhone(editCustomer.getPhone());
            customer.setAccountStatus(editCustomer.isAccountStatus());
           this.customerRepository.save(customer);
        });
        model.setViewName("customers");
        model.addObject("customerDatas",this.customerRepository.findAllByOrderByNameAsc());
        return model;
    }

    @DeleteMapping
    public void deleteCustomer(@PathVariable String id) {
        customerRepository.deleteById(id);
    }

    @GetMapping("/getCustomer")
    public ModelAndView getCustomerbyId (@RequestParam(value = "customerId") String id,ModelAndView model) {
        model.setViewName("editcustomer");
        model.addObject("customerData",this.customerRepository.findById(id));
        return model;
    }

}
