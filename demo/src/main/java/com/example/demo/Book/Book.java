package com.example.demo.Book;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document(collection ="book")
public class Book {

    @Id
    private String id;
    private String name;
    @Indexed
    private int price;
    private Date publishdate;
    private String charactorsList;
    private boolean status;

    public Book(){

    }

    public Book( String name, Date publishdate,int price , String charactorsList, boolean status) {
        this.name = name;
        this.publishdate = publishdate;
        this.charactorsList = charactorsList;
        this.price = price;
        this.status = status;
    }

    public void set_id(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    public void setCharactorsList(String charactorsList) {
        this.charactorsList = charactorsList;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public String getCharactorsList() {
        return charactorsList;
    }

    public boolean isStatus() { return status; }
}
