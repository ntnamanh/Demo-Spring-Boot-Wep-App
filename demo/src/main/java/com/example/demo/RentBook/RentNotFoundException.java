package com.example.demo.RentBook;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RentNotFoundException extends RuntimeException {
    public RentNotFoundException(String message,Throwable cause){
        super(message,cause);
    }
    public RentNotFoundException(){
        super();
    }
    public RentNotFoundException(String message){
        super(message);
    }
    public RentNotFoundException(Throwable cause){
        super(cause);
    }
}
