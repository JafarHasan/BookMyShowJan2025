package com.BookMyShowJan2025.BookMyShow.Repositories;

import com.BookMyShowJan2025.BookMyShow.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {
    Movie findByMovieName(String movieName);
}
