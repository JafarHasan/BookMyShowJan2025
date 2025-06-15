package com.BookMyShowJan2025.BookMyShow.Exceptions;

public class EmptyTheatreListException extends  RuntimeException{
    public EmptyTheatreListException(String message){
        super(message);
    }
}
