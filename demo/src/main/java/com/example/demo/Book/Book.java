package com.example.demo.Book;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="book")
public class Book {
    @Id
    private String id;
    private String name;
    @Indexed
    private int price;
    private String category;
    private String bookStatus;

    public Book() {

    }

    public Book( String name,int price , String category, String bookStatus) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.bookStatus = bookStatus;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setBookStatus(String book_status) {
        this.bookStatus = book_status;
    }

    public int getPrice() {
        return price;
    }

    public String getId() { return id; }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getBookStatus() { return bookStatus; }
}
