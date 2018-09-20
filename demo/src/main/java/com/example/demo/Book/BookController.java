package com.example.demo.Book;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


@RestController
@RequestMapping(value = "/Book")
public class BookController {
    private BookRepository bookRepository;
    public BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @GetMapping(value = "/all")
    public List<Book> getAllBook(){
        List<Book> allbook = this.bookRepository.findAll();
        return allbook;
    }


}
