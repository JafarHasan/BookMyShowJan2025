package com.BookMyShowJan2025.BookMyShow.Interfaces;

import com.BookMyShowJan2025.BookMyShow.DTOs.BookTicketDto;
import com.BookMyShowJan2025.BookMyShow.Models.Ticket;
import com.BookMyShowJan2025.BookMyShow.Response.TicketResponse;

import java.util.List;

public interface TicketInterface {

    // Create a ticket
    TicketResponse bookTicket(BookTicketDto bookTicketDto) throws Exception;

    // Fetch all tickets
    List<Ticket> getAllTickets() throws Exception;

    // Fetch a ticket by ID
    TicketResponse getTicketById(String ticketId) throws Exception;

    // Update a ticket's details
    TicketResponse updateTicket(String ticketId, BookTicketDto updatedTicketDto) throws Exception;

    // Delete a ticket
    String deleteTicket(String ticketId) throws Exception;
}
