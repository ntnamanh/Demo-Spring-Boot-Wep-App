package com.example.demo;

import com.example.demo.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {

    private BookRepository bookRepository;
    public DbSeeder(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

    @Override
    public void run(String... args) throws Exception {
        Charactors charact1 = new Charactors("C1","Bird");
        Charactors charact2 = new Charactors("C2","Tu");
        Charactors charact3 = new Charactors("C3","Nhat");
        List<Charactors> charactorsList = Arrays.asList(charact1,charact2);
        List<Charactors> charactorsList1 = Arrays.asList(charact1,charact3);
        Book newbook1 = new Book("Little Bird",simpleDateFormat.parse("03/07/1994"),200,charactorsList);
        Book newbook2 = new Book("Bird and family",simpleDateFormat.parse("20/09/2018"),150,charactorsList1);
        List<Book> bookList = Arrays.asList(newbook1,newbook2);
        this.bookRepository.deleteAll();

        for (Book book: bookList
             ) {
            this.bookRepository.save(book);
        }


    }
}
