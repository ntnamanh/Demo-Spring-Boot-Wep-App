package com.example.demo.Book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class BookDbSeeder implements CommandLineRunner {

    private BookRepository bookRepository;
    public BookDbSeeder(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        Book newbook1 = new Book("Little Bird",10,"horror","Available");
        Book newbook2 = new Book("Bird and family",12,"romance","Available");
        Book newbook3 = new Book("Bird and his happy days",15,"adventure","Available");
        List<Book> bookList = Arrays.asList(newbook1,newbook2,newbook3);
        this.bookRepository.deleteAll();
        for (Book book: bookList) {
            this.bookRepository.save(book);
        }
    }
}
