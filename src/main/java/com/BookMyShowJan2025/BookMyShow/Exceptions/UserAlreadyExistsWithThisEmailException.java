package com.BookMyShowJan2025.BookMyShow.Exceptions;

public class UserAlreadyExistsWithThisEmailException extends  RuntimeException{
    public UserAlreadyExistsWithThisEmailException(String message){
        super(message);
    }
}
