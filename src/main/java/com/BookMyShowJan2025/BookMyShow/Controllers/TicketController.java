package com.BookMyShowJan2025.BookMyShow.Controllers;

import com.BookMyShowJan2025.BookMyShow.DTOs.BookTicketDto;
import com.BookMyShowJan2025.BookMyShow.Repositories.TicketRepository;
import com.BookMyShowJan2025.BookMyShow.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;
    private final TicketRepository ticketRepository;
    @Autowired
    public TicketController(TicketService ticketService,
                            TicketRepository ticketRepository){
        this.ticketRepository=ticketRepository;
        this.ticketService=ticketService;
    }
    //POST http://localhost:8080/ticket/book
    @PostMapping("/book")
    public ResponseEntity<String> bookTicket(@RequestBody BookTicketDto bookTicketDto){
        return new ResponseEntity<>(ticketService.bookTicket(bookTicketDto), HttpStatus.CREATED);
    }

}
