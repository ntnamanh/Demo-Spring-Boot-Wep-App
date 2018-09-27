package com.example.demo.Customer;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "customer")
public class Customer {

    @Id
    private String id;
    private String name;
    private String address;
    private String phone;
    private String email;
    @Indexed
    private int bookRents;
    private Date registedDate;
    private boolean accountStatus;
    public Customer(){

    }


    public Customer(String name, String address, String phone, String email, int bookRents, Date registedDate , boolean accountStatus) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.bookRents = bookRents;
        this.registedDate = registedDate;
        this.accountStatus = accountStatus;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBookRents(int bookRents) { this.bookRents = bookRents; }

    public void setRegistedDate(Date registedDate) { this.registedDate = registedDate; }


    public void setAccountStatus(boolean accountStatus) { this.accountStatus = accountStatus; }

    public int getBookRents() { return bookRents; }

    public boolean isAccountStatus() { return accountStatus; }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Date getRegisted_date() { return registedDate; }
}
