package com.BookMyShowJan2025.BookMyShow.Exceptions;

import com.BookMyShowJan2025.BookMyShow.Interfaces.TicketInterface;

public class TicketNotFoundException extends RuntimeException{
    public TicketNotFoundException(String message){
        super(message);
    }
}
