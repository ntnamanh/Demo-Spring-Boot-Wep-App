package com.example.demo.RentBook;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Rent")
public class Rent {
    @Id
    private String id;
    private String customerId;
    private String bookId;
    private Date startDate;
    private Date endDate;
    @Indexed
    private int paid;
    private boolean returnStatus;
    public Rent(){

    }

    public Rent(String customerId, String bookId, Date startDate, Date endDate, int paid, boolean returnStatus) {
        this.customerId = customerId;
        this.bookId = bookId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.paid = paid;
        this.returnStatus = returnStatus;
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public boolean isReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(boolean returnStatus) {
        this.returnStatus = returnStatus;
    }
}
