package com.BookMyShowJan2025.BookMyShow.Exceptions;

public class ShowNotFoundException extends RuntimeException{
    public ShowNotFoundException(String message){
        super(message);
    }
}
