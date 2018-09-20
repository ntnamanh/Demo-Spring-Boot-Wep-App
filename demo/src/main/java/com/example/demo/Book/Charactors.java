package com.example.demo.Book;

public class Charactors {

    private String charactorsID;
    private String name;
    public Charactors(){

    }
    public Charactors(String charactorsID,String name){
        this.charactorsID = charactorsID;
        this.name = name;
    }

    public String getCharactorsID() {
        return charactorsID;
    }

    public String getName() {
        return name;
    }
}
