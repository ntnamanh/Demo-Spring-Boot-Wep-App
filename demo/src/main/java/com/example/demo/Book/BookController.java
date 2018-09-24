package com.example.demo.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.rmi.CORBA.Util;
import java.text.ParseException;
import java.text.SimpleDateFormat;



@RestController
@RequestMapping(value = "/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;
    public BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

    @GetMapping()
    public ModelAndView getAllBook(ModelAndView model){
        model.setViewName("books");
        model.addObject("book_datas",this.bookRepository.findAll());
        return model;
    }

    @GetMapping("/search")
    public ModelAndView get_book_by_id(@RequestParam(value = "search", required = true) String search, ModelAndView model){
        model.setViewName("books");
        if(search.isEmpty() || search.trim().equals(""))
            model.addObject("book_datas",this.bookRepository.findAll());
        else
            model.addObject("book_datas", this.bookRepository.findAllByNameLike(search));
        return model;
    }
    @GetMapping("/under/{price}")
    public ModelAndView under100(ModelAndView model,@PathVariable String price){
        model.setViewName("books");
        model.addObject("book_datas",this.bookRepository.findAllByPriceLessThanOrderByPriceAsc(Integer.parseInt(price)));
        return model;
    }
//    @GetMapping("/Under200")
//    public ModelAndView under200(ModelAndView model){
//        model.setViewName("books");
//        model.addObject("book_datas",this.bookRepository.findAllByPriceLessThan(200));
//        return model;
//    }
//    @GetMapping("/Under300")
//    public ModelAndView under300(ModelAndView model){
//        model.setViewName("books");
//        model.addObject("book_datas",this.bookRepository.findAllByPriceLessThan(300));
//        return model;
//    }

    @PostMapping("/book")
    public void insert_book(@PathVariable String name,@PathVariable String publish,@PathVariable String price,@PathVariable String char_name) throws ParseException {
        bookRepository.save(new Book(name,simpleDateFormat.parse(publish),Integer.parseInt(price),char_name,true));
    }
    @DeleteMapping(value = "/{id}")
    public void delete_book(@PathVariable String id){
        bookRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void edit(@RequestBody Book newBook,@PathVariable String id){
        bookRepository.findById(id).map(book->{
            book.setName(newBook.getName());
            book.setCharactorsList(newBook.getCharactorsList());
            book.setPrice(newBook.getPrice());
            book.setPublishdate(newBook.getPublishdate());
            book.setStatus(newBook.isStatus());
            Book updatebook = bookRepository.save(book);
        return ResponseEntity.ok().body(updatebook);
        }).orElseThrow(()-> new BookNotFoundException("Can find Book with the ID"))
            ;
    }

}
