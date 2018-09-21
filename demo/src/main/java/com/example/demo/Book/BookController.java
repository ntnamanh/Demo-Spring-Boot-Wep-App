package com.example.demo.Book;


import com.example.demo.Customer.CustomerNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


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
    public List<Book> getAllBook(){
        List<Book> allbook = this.bookRepository.findAll();
        return allbook;
    }

    @GetMapping("/{id}")
    public Book get_book_by_id(@PathVariable String id){
        return bookRepository.findById(id).orElseThrow(()->new BookNotFoundException("Can find Book with id"));
    }
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
   //     bookRepository.save(newBook);
    }

}
