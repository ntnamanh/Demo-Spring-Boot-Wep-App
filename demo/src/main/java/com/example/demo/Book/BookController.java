package com.example.demo.Book;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.Optional;


@Controller
@RequestMapping(value = "/books")
public class BookController {

    private BookRepository bookRepository;
    public BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @GetMapping()
    public ModelAndView getAllBook(ModelAndView model) {
        model.setViewName("books");
        model.addObject("bookDatas",this.bookRepository.findAllByOrderByNameAsc());
        return model;
    }

    @GetMapping("/search")
    public ModelAndView searchBook(@RequestParam(value = "search", required = true) String search, ModelAndView model){
        model.setViewName("books");
        if(search.isEmpty() || search.trim().equals("")) {
            model.addObject("bookDatas",this.bookRepository.findAll());
        } else {
            model.addObject("bookDatas", this.bookRepository.findAllByNameLike(search));
        }
        return model;
    }
    @GetMapping("/type/{bookStatus}")
    public ModelAndView searchByType(@PathVariable String bookStatus,ModelAndView model){
        model.setViewName("books");
        model.addObject("bookDatas",this.bookRepository.findAllByBookStatusEquals(bookStatus));
        return model;
    }

    @GetMapping("/getbyid")
    public ModelAndView getBook(@RequestParam(value = "edit") String id,ModelAndView model) {
        model.setViewName("editbook");
        Optional<Book> book =  this.bookRepository.findById(id);
        model.addObject("bookD",book);
        return model;
    }

    @PostMapping
    public ModelAndView insertBook(@ModelAttribute(value = "newBook")  Book book,ModelAndView model) {
        Book newbook = new Book(book.getName(),book.getPrice(),book.getCategory(),book.getBookStatus());
        bookRepository.save(newbook);
        model.setViewName("books");
        model.addObject("bookDatas",this.bookRepository.findAllByOrderByNameAsc());
        return model;
    }

    @DeleteMapping
    public ModelAndView deleteBook(@RequestParam(value = "bookId") String id, ModelAndView model) {
        this.bookRepository.deleteById(id);
        model.setViewName("books");
        model.addObject("bookDatas",this.bookRepository.findAllByOrderByNameAsc());
        return model;
    }


    @PutMapping
    public ModelAndView edit(@ModelAttribute(value = "editBook") Book editbook,@RequestParam(value = "id") String id,ModelAndView model) {
        this.bookRepository.findById(id).ifPresent(book -> {
            book.setName(editbook.getName());
            book.setCategory(editbook.getCategory());
            book.setPrice(editbook.getPrice());
            book.setBookStatus(editbook.getBookStatus());
            this.bookRepository.save(book);
        });
        model.setViewName("books");
        model.addObject("bookDatas",this.bookRepository.findAllByOrderByNameAsc());
        return model;
    }

}
