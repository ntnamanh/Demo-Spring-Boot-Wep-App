package com.example.demo.RentBook;



import com.example.demo.Book.Book;
import com.example.demo.Book.BookRepository;
import com.example.demo.Customer.Customer;
import com.example.demo.Customer.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping(value = "/rents")
public class RentController {
    private RentRepository rentRepository;
    private BookRepository bookRepository;
    private CustomerRepository customerRepository;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

    public RentController(RentRepository rentRepository,BookRepository bookRepository,CustomerRepository customerRepository){
        this.rentRepository = rentRepository;
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping()
    public ModelAndView getAll(ModelAndView model){
        model.setViewName("rents");
        model.addObject("rentDatas", this.rentRepository.findAllByOrderByStartDateDesc());
        return model;
    }

    @GetMapping("/return/{value}")
    public ModelAndView find_by_return(ModelAndView model, @PathVariable String value){
            boolean check;
            model.setViewName("rents");
            check = value.equals("returned");
            model.addObject("rentDatas",this.rentRepository.findAllByReturnStatusIs(check));
            return model;
    }


    @GetMapping("/search")
    public ModelAndView searchByCusId(@RequestParam(value = "customerId")String customerId, ModelAndView model){
        model.setViewName("rents");
        model.addObject("rentDatas",this.rentRepository.findAllByCustomerIdEqualsAndReturnStatusIs(customerId,false));
        return model;
    }

    @PostMapping
    public ModelAndView addRent(@ModelAttribute(value = "newRent") Rent newRent,@RequestParam(value = "customerId")String customerId ,ModelAndView model ){
        Calendar calendar = Calendar.getInstance();
        if(checkBook(newRent.getBookId())){
            model.setViewName("errorhandler");
            model.addObject("message","Can not find any books match with this ID");
            return model;
        }
        if(!checkAvailable(newRent.getBookId())){
            model.setViewName("errorhandler");
            model.addObject("message","This book is rented or up-comming");
            return model;
        }
        Rent rent = new Rent(customerId,newRent.getBookId(),calendar.getTime(),null,0,false);
        updateRent(customerId,newRent.getBookId());
        this.rentRepository.save(rent);
        model.setViewName("rents");
        model.addObject("rentDatas",this.rentRepository.findAllByOrderByStartDateDesc());
        return model;
    }

    @PostMapping("/returnBook")
    public ModelAndView returnBook( @RequestParam(value = "rentId") String id, ModelAndView model){
        Calendar calendar = Calendar.getInstance();
        Date endDate = calendar.getTime();
        this.rentRepository.findById(id).ifPresent(rent->{
            rent.setEndDate(endDate);
            rent.setPaid(calculateMoney(rent.getStartDate(),endDate,rent.getBookId()));
            rent.setReturnStatus(true);
            updateReturn(rent.getCustomerId(),rent.getBookId());
            this.rentRepository.save(rent);

        });
        model.setViewName("rents");
        model.addObject("rentDatas",this.rentRepository.findAllByOrderByStartDateDesc());
        return model;
    }
    @DeleteMapping
    public ModelAndView deleteRent(@RequestParam(value = "rentId") String id,ModelAndView model){
        this.rentRepository.deleteById(id);
        model.setViewName("rents");
        model.addObject("rentDatas",this.rentRepository.findAllByOrderByStartDateDesc());
        return  model;
    }

    private int calculateMoney(Date stateDate, Date endDate, String bookId) {
        int price = this.bookRepository.findById(bookId).get().getPrice();
        if(simpleDateFormat.format(stateDate).equals(simpleDateFormat.format(endDate))){
            return price;
        }
        float millisecond =  stateDate.getTime() - endDate.getTime();
        double date = Math.ceil(((millisecond / (1000 * 60 * 60)) % 24)/24);
        return (int)(date *price);
    }
    private void updateRent(String customerId,String bookrId){
        this.customerRepository.findById(customerId).map(customer -> {
            customer.setBookRents(customer.getBookRents()+1);
            Customer editCustomer = this.customerRepository.save(customer);
            return ResponseEntity.ok(editCustomer);
        });
        this.bookRepository.findById(bookrId).map(book -> {
           book.setBookStatus("Unavailable");
           Book editBook  = this.bookRepository.save(book);
           return ResponseEntity.ok(editBook);
        });
    }

    private void updateReturn(String customerId,String bookrId){
        this.customerRepository.findById(customerId).ifPresent(customer -> {
           customer.setBookRents(customer.getBookRents()-1);
           this.customerRepository.save(customer);
        });
        this.bookRepository.findById(bookrId).ifPresent(book -> {
            book.setBookStatus("Available");
            this.bookRepository.save(book);
        });
    }

    private boolean checkBook (String bookId){
        return !this.bookRepository.findById(bookId).isPresent();
    }

    private boolean checkAvailable(String bookId){
        Optional<Book> book = this.bookRepository.findById(bookId);
        return book.map(book1 -> book1.getBookStatus().equals("Available")).orElse(false);
    }
}
