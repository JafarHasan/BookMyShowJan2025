package com.BookMyShowJan2025.BookMyShow.Controllers;

import com.BookMyShowJan2025.BookMyShow.DTOs.MovieDTO;
import com.BookMyShowJan2025.BookMyShow.Models.Movie;
import com.BookMyShowJan2025.BookMyShow.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/add")
    public ResponseEntity<Movie> addMovie(@RequestBody MovieDTO movieDTO) {
        Movie movie = movieService.addMovie(movieDTO);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    //GET:http://localhost:8080/movie/getMovieByName?movieName=Historic Tales
    @GetMapping("/getMovieByName")
    public ResponseEntity<Movie> getMovieByName(@RequestParam String movieName){
        Movie movie=movieService.getMovieByName(movieName);
        return new ResponseEntity<>(movie,HttpStatus.OK);
    }

    //GET:http://localhost:8080/movie/getAllMovies
    @GetMapping("/getAllMovies")
    public ResponseEntity<List<Movie>> getAllMovie(){
        List<Movie> movieList=movieService.getAllMovie();
        return new ResponseEntity<>(movieList,HttpStatus.OK);
    }

    //PUT:http://localhost:8080/movie/update-movie-by-name?movieName=Drama Unfolds
    // (ans movieDTO in requestBody)
    @PutMapping("/update-movie-by-name")
    public ResponseEntity<Movie> updateMovieByMovieName(@RequestParam String movieName,
                                                              @RequestBody MovieDTO movieDTO) {
        Movie movie = movieService.updateMovieByMovieName(movieName,movieDTO);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }
    //DELETE:http://localhost:8080/movie/delete-movie-by-id?id=10
    @DeleteMapping("/delete-movie-by-id")
    public ResponseEntity<String> deleteMovieById(@RequestParam Integer id) {
        String response = movieService.deleteMovieById(id);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    //http://localhost:8080/movie/get-movie-with-pagination?page=0&size=3 on the 0th page we want 3 enteries
    @GetMapping("/get-movie-with-pagination")
    public Iterable<Movie> getMovieWithPagination(@RequestParam Integer page,
                                                  @RequestParam Integer size){
        return movieService.getMovieWithPagination(page,size);
    }

}
