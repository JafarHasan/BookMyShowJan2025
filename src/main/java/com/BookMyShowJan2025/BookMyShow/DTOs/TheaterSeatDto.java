package com.BookMyShowJan2025.BookMyShow.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheaterSeatDto {

    private Integer theaterId;
    private String seatName;
    private String seatType;
}
