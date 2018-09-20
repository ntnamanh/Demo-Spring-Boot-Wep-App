package com.example.demo.Book;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection ="Book")
public class Book {
    @Id
    private String ID;
    private String Name;
    @Indexed
    private int price;
    private Date publishdate;
    private List<Charactors> charactorsList;
    private boolean status;

    public Book(){
        this.charactorsList = new ArrayList<>();
    }

    public Book( String name, Date publishdate,int price , List<Charactors> charactorsList, boolean status) {
        this.Name = name;
        this.publishdate = publishdate;
        this.charactorsList = charactorsList;
        this.price = price;
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public List<Charactors> getCharactorsList() {
        return charactorsList;
    }

    public boolean isStatus() { return status; }
}
