package com.example.demo.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


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
    public ModelAndView under100(@PathVariable String price,ModelAndView model){
        model.setViewName("books");
        model.addObject("book_datas",this.bookRepository.findAllByPriceLessThanOrderByPriceAsc(Integer.parseInt(price)));
        return model;
    }

    @PostMapping("/add")
    public ModelAndView insert_book(@ModelAttribute(value = "new_book")  Book book,ModelAndView model)  {
        Book newbook = new Book(book.getName(),book.getPrice(),book.getCategory(),book.getStatus());
        bookRepository.save(newbook);
        model.setViewName("books");
        model.addObject("book_datas",this.bookRepository.findAll());
        return model;
    }
    @DeleteMapping("/delete")
    public ModelAndView delete_book(@RequestParam(value = "book_id") String id, ModelAndView model){
        this.bookRepository.deleteById(id);
        model.setViewName("books");
        model.addObject("book_datas",this.bookRepository.findAll());
        return model;
    }

//    @PutMapping("/{id}")
//    public void edit(@RequestBody Book newBook,@PathVariable String id){
//        bookRepository.findById(id).map(book->{
//            book.setName(newBook.getName());
//            book.setCategory(newBook.getCategory());
//            book.setPrice(newBook.getPrice());
//            book.setStatus(newBook.getStatus());
//            Book updatebook = bookRepository.save(book);
//        return ResponseEntity.ok().body(updatebook);
//        }).orElseThrow(()-> new BookNotFoundException("Can find Book with the ID"))
//            ;
//    }

}
