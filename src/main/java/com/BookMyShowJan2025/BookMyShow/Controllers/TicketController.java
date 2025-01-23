package com.BookMyShowJan2025.BookMyShow.Controllers;

import com.BookMyShowJan2025.BookMyShow.Repositories.TicketRepository;
import com.BookMyShowJan2025.BookMyShow.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
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



}
