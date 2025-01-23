package com.BookMyShowJan2025.BookMyShow.Repositories;

import com.BookMyShowJan2025.BookMyShow.Models.Movie;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MoviePagingAndSortingRepository extends PagingAndSortingRepository<Movie,Integer> {

}
