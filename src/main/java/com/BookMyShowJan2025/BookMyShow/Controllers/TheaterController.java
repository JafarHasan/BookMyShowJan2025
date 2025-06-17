package com.BookMyShowJan2025.BookMyShow.Controllers;

import com.BookMyShowJan2025.BookMyShow.DTOs.*;
import com.BookMyShowJan2025.BookMyShow.Exceptions.TheaterNotFoundException;
import com.BookMyShowJan2025.BookMyShow.Models.Movie;
import com.BookMyShowJan2025.BookMyShow.Models.Theater;
import com.BookMyShowJan2025.BookMyShow.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
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
    //GET:http://localhost:8080/theater/search-theater-by-name?name=cinepolis
    @GetMapping("/search-theater-by-name")
    public ResponseEntity<GetTheaterDTO> getTheaterByName(@RequestParam String name){
        return new ResponseEntity<>(theaterService.getTheaterByName(name.toLowerCase()),HttpStatus.OK);

    }
    //DELETE:http://localhost:8080/theater/delete-theater-by-id?theaterId=1
    @DeleteMapping("/delete-theater-by-id")
    public ResponseEntity<String> deleteTheaterById(@RequestParam Integer theaterId) {
        String response = theaterService.deleteTheaterById(theaterId);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }
    @PutMapping("associate-theater-with-theaterSeat")
    public ResponseEntity<String> associateTheaterWithTheaterSeat(@RequestBody TheaterSeatDto theaterSeatDto) {
        String response=theaterService.associateTheaterWithSeat(theaterSeatDto);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }
    //GET:http://localhost:8080/theater/get-theater-by-location/DeLHi
    @GetMapping("/get-theater-by-location/{location}")
    public ResponseEntity<List<TheatreListDTO>> getTheaterByLocation(@PathVariable String location){
        return new ResponseEntity<>(theaterService.getTheaterByLocation(location),HttpStatus.OK);

    }
    //GET:http://localhost:8080/theater/get-all-theater-list
    @GetMapping("get-all-theater-list")
    public ResponseEntity<List<TheatreListDTO>> getAllTheatreList(){
        return  new ResponseEntity<>(theaterService.getAllTheatre(),HttpStatus.OK);
    }

}
