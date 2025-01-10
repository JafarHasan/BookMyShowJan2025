package com.BookMyShowJan2025.BookMyShow.Controllers;

import com.BookMyShowJan2025.BookMyShow.DTOs.MovieDTO;
import com.BookMyShowJan2025.BookMyShow.DTOs.TheaterDto;
import com.BookMyShowJan2025.BookMyShow.Models.Movie;
import com.BookMyShowJan2025.BookMyShow.Models.Theater;
import com.BookMyShowJan2025.BookMyShow.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    private final TheaterService theaterService;
    @Autowired
    TheaterController(TheaterService theaterService){
        this.theaterService=theaterService;
    }
    //POST:http://localhost:8080/theater/add +dto in body(JSON)
    @PostMapping("/add")
     public ResponseEntity<Theater> addTheater(@RequestBody TheaterDto theaterDto) {
        Theater theater = theaterService.addTheater(theaterDto);
        return new ResponseEntity<>(theater, HttpStatus.OK);
    }

    //DELETE:http://localhost:8080/theater/delete-theater-by-id?theaterId=1
    @DeleteMapping("/delete-theater-by-id")
    public ResponseEntity<String> deleteTheaterById(@RequestParam Integer theaterId) {
        String response = theaterService.deleteTheaterById(theaterId);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

}
