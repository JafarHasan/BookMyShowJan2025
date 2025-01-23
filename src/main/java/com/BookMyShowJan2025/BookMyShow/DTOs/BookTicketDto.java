package com.BookMyShowJan2025.BookMyShow.DTOs;

import lombok.Data;
import java.util.List;

@Data
public class BookTicketDto {
    private List<String> requestedSeats;
    private Integer showId;
    private Integer userId;
}
