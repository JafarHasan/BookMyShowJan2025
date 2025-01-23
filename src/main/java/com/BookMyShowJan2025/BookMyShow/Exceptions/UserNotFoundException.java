package com.BookMyShowJan2025.BookMyShow.Exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }
}
