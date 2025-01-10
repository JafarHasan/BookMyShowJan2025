package com.BookMyShowJan2025.BookMyShow.Repositories;

import com.BookMyShowJan2025.BookMyShow.Models.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends JpaRepository<Theater,Integer> {

    Theater findByTheaterNameAndAddress(String theaterName,String address);
}
