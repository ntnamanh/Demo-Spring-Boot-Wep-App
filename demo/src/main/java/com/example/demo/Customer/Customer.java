package com.example.demo.Customer;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer")
public class Customer {

    @Id
    private String id;
    private String name;
    private String address;
    private String phone;
    private String email;
    @Indexed
    private int numberofrent;
    private boolean status;
    public Customer(){

    }

    public Customer(String name, String address, String phone, String email, int numberofrent,boolean status) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.numberofrent = numberofrent;
        this.status = status;
    }

    public void set_id(String id) {
        this.id = id;
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

    public void setNumberofrent(int numberofrent) {
        this.numberofrent = numberofrent;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getNumberofrent() {
        return numberofrent;
    }

    public boolean isStatus() {
        return status;
    }

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
}
