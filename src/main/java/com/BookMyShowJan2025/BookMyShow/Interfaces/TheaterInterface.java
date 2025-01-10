package com.BookMyShowJan2025.BookMyShow.Interfaces;

import com.BookMyShowJan2025.BookMyShow.DTOs.TheaterDto;
import com.BookMyShowJan2025.BookMyShow.Models.Theater;

public interface TheaterInterface {
    Theater addTheater(TheaterDto theaterDto) throws Exception;

    String deleteTheaterById(Integer theaterId) throws Exception;
}
