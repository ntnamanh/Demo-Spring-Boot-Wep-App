package com.example.demo.Book;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection ="book")
public class Book {
    @Id
    private String _id;
    private String Name;
    @Indexed
    private int price;
    private Date publishdate;
    private String charactorsList;
    private boolean status;

    public Book(){

    }

    public Book( String name, Date publishdate,int price , String charactorsList, boolean status) {
        this.Name = name;
        this.publishdate = publishdate;
        this.charactorsList = charactorsList;
        this.price = price;
        this.status = status;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setName(String name) {
        Name = name;
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
        return _id;
    }

    public String getName() {
        return Name;
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public String getCharactorsList() {
        return charactorsList;
    }

    public boolean isStatus() { return status; }
}
