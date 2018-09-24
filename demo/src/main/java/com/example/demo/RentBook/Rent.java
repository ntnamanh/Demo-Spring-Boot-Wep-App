package com.example.demo.RentBook;

import org.omg.PortableServer.SERVANT_RETENTION_POLICY_ID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Rent")
public class Rent {
    @Id
    private String id;
    private String customerID;
    private String bookID;
//    private String start_date;
//    private String end_date;
    private boolean _return;

    public Rent(){

    }
//    public void setStart_date(Date start_date) {
//        this.start_date = start_date;
//    }
//
//    public void setEnd_date(Date end_date) {
//        this.end_date = end_date;
//    }

    public void set_return(boolean _return) {
        this._return = _return;
    }

    public Rent(String customerID, String bookID, boolean _return) {
        this.customerID = customerID;
        this.bookID = bookID;
//        this.start_date = start_date;
//        this.end_date = end_date;
        this._return = _return;
    }

    public void set_id(String id) {
        this.id = id;
    }
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
    public void setBookID(String bookID) {
        this.bookID = bookID;
    }
    public String getId() {
        return id;
    }
    public String getCustomerID() {
        return customerID;
    }
    public String getBookID() {
        return bookID;
    }
    public boolean is_return() {
        return _return;
    }
//    public Date getStart_date() {
//        return start_date;
//    }
//
//    public Date getEnd_date() {
//        return end_date;
//    }


}
