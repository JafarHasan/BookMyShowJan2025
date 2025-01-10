package com.BookMyShowJan2025.BookMyShow.Interfaces;

import com.BookMyShowJan2025.BookMyShow.DTOs.MovieDTO;
import com.BookMyShowJan2025.BookMyShow.Models.Movie;

import java.util.List;

public interface MovieInterface {
    Movie addMovie(MovieDTO movieDTO) throws Exception;

    Movie getMovieByName(String movieName) throws Exception;

    List<Movie> getAllMovie() throws Exception;

    Movie updateMovieByMovieName(String movieName,MovieDTO updateMovieDto) throws Exception;

    String deleteMovieById(Integer id) throws Exception;

}
