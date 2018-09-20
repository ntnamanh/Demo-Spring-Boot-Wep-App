package com.example.demo.Book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@Component
public class BookDbSeeder implements CommandLineRunner {

    private BookRepository bookRepository;
    public BookDbSeeder(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

    @Override
    public void run(String... args) throws Exception {

        Book newbook1 = new Book("Little Bird",simpleDateFormat.parse("03/07/1994"),200,"Bird,Tu,Nhat",true);
        Book newbook2 = new Book("Bird and family",simpleDateFormat.parse("20/09/2018"),150,"Binh,Nhat,Minh",true);
        List<Book> bookList = Arrays.asList(newbook1,newbook2);
        this.bookRepository.deleteAll();

        for (Book book: bookList
             ) {
            this.bookRepository.save(book);
        }


    }
}
