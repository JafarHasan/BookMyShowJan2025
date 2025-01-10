package com.BookMyShowJan2025.BookMyShow.Exceptions;

public class MovieNotFoundException extends RuntimeException{
    public MovieNotFoundException(String message){
        super(message);
    }
}
