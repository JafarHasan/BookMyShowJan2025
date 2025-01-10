package com.BookMyShowJan2025.BookMyShow.Exceptions;

public class MovieAlreadyExists extends RuntimeException{
    public MovieAlreadyExists(String message){
        super(message);
    }
}
