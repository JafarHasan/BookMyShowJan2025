package com.BookMyShowJan2025.BookMyShow.Exceptions;

public class TheaterAlreadyExistsException extends RuntimeException{
    public TheaterAlreadyExistsException(String message){
        super(message);
    }
}
