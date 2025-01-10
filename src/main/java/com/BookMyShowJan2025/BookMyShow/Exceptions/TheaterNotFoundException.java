package com.BookMyShowJan2025.BookMyShow.Exceptions;

public class TheaterNotFoundException extends RuntimeException{
    public TheaterNotFoundException(String message){
        super(message);
    }
}
