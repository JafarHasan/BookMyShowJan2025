package com.BookMyShowJan2025.BookMyShow.Interfaces;

import com.BookMyShowJan2025.BookMyShow.DTOs.ShowDto;
import com.BookMyShowJan2025.BookMyShow.Models.Show;

public interface ShowInterface {
    String addShow(ShowDto showDto) throws Exception;
    String deleteShow(Integer showId) throws Exception;
}
