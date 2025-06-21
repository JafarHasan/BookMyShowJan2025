package com.BookMyShowJan2025.BookMyShow.Controllers;

import com.BookMyShowJan2025.BookMyShow.DTOs.MovieDTO;
import com.BookMyShowJan2025.BookMyShow.Exceptions.MovieNotFoundException;
import com.BookMyShowJan2025.BookMyShow.Models.Movie;
import com.BookMyShowJan2025.BookMyShow.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/movie")

public class MovieController {
    private final MovieService movieService;


    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

   // @PreAuthorize("hasRole('ADMIN')")
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
    //GET:http://localhost:8080/movie/update-movie-by-id/2
    // (ans movieDTO in requestBody)
    @PutMapping("/update-movie-by-id/{movieId}")
    public ResponseEntity<String> updateMovieByMovieId(@PathVariable Integer movieId,
                                                        @RequestBody MovieDTO movieDTO) {
        String res = movieService.updateMovieByMovieId(movieId,movieDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    //GET:http://localhost:8080/movie/get-movie-by-id/1
    @GetMapping("/get-movie-by-id/{movieId}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable Integer movieId) {
        MovieDTO dto=movieService.getMovieById(movieId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    //DELETE:http://localhost:8080/movie/delete-movie-by-id?id=10
    @DeleteMapping("/delete-movie-by-id")
    public ResponseEntity<String> deleteMovieById(@RequestParam Integer id) {
        String response = movieService.deleteMovieById(id);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    //http://localhost:8080/movie/get-movie-with-pagination?page=0&size=3 on the 0th page we want 3 enteries
//    @GetMapping("/get-movie-with-pagination")
//    public Iterable<Movie> getMovieWithPagination(@RequestParam Integer page,
//                                                  @RequestParam Integer size){
//        return movieService.getMovieWithPagination(page,size);
//    }
    @GetMapping("/get-movie-with-pagination")
    public ResponseEntity<Page<Movie>> getMovieWithPagination(@RequestParam Integer page,
                                                              @RequestParam Integer size){
        return new ResponseEntity<>(movieService.getMovieWithPagination(page, size), HttpStatus.OK);
    }

    //GET:http://localhost:8080/movie/get-movie-list-by-genre/EPIC
    @GetMapping("/get-movie-list-by-genre/{genre}")
    public ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre){
        return new ResponseEntity<>(movieService.getMovieByGenre(genre),HttpStatus.OK);
    }

}
