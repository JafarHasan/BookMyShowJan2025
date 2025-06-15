package com.BookMyShowJan2025.BookMyShow.Repositories;

import com.BookMyShowJan2025.BookMyShow.Enum.Genre;
import com.BookMyShowJan2025.BookMyShow.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.processing.Generated;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {
    Movie findByMovieName(String movieName);
    List<Movie> findAllByGenre(Genre genre);
}
