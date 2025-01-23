package com.BookMyShowJan2025.BookMyShow.Repositories;

import com.BookMyShowJan2025.BookMyShow.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer> {

}
