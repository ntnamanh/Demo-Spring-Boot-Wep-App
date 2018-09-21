package com.example.demo.RentBook;

import org.omg.PortableServer.SERVANT_RETENTION_POLICY_ID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Rent")
public class Rent {
    @Id
    private String _id;
    private String customerID;
    private String bookID;
    private Date start_date;
    private Date end_date;
    private boolean _return;

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public void set_return(boolean _return) {
        this._return = _return;
    }

    public Rent(String customerID, String bookID, Date start_date, Date end_date, boolean _return) {
        this.customerID = customerID;
        this.bookID = bookID;
        this.start_date = start_date;
        this.end_date = end_date;
        this._return = _return;
    }

    public String getId() {
        return _id;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getBookID() {
        return bookID;
    }

    public Date getStart_date() {
        return start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public boolean is_return() {
        return _return;
    }
}
