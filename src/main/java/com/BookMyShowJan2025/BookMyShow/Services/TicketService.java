package com.BookMyShowJan2025.BookMyShow.Services;

import com.BookMyShowJan2025.BookMyShow.DTOs.BookTicketDto;
import com.BookMyShowJan2025.BookMyShow.Interfaces.TicketInterface;
import com.BookMyShowJan2025.BookMyShow.Models.Ticket;
import com.BookMyShowJan2025.BookMyShow.Response.TicketResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService implements TicketInterface {


    @Override
    public TicketResponse bookTicket(BookTicketDto bookTicketDto) throws Exception {
        return null;
    }

    @Override
    public List<Ticket> getAllTickets() throws Exception {
        return List.of();
    }

    @Override
    public TicketResponse getTicketById(String ticketId) throws Exception {
        return null;
    }

    @Override
    public TicketResponse updateTicket(String ticketId, BookTicketDto updatedTicketDto) throws Exception {
        return null;
    }

    @Override
    public String deleteTicket(String ticketId) throws Exception {
        return "";
    }
}
