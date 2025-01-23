package com.BookMyShowJan2025.BookMyShow.Services;

import com.BookMyShowJan2025.BookMyShow.DTOs.MovieDTO;
import com.BookMyShowJan2025.BookMyShow.Exceptions.MovieAlreadyExists;
import com.BookMyShowJan2025.BookMyShow.Exceptions.MovieNotFoundException;
import com.BookMyShowJan2025.BookMyShow.Interfaces.MovieInterface;
import com.BookMyShowJan2025.BookMyShow.Models.Movie;
import com.BookMyShowJan2025.BookMyShow.Repositories.MoviePagingAndSortingRepository;
import com.BookMyShowJan2025.BookMyShow.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements MovieInterface {

    //constructor based dependency Injection
    private final MovieRepository movieRepository;
    private final MoviePagingAndSortingRepository moviePagingAndSortingRepository;
    @Autowired
    public MovieService(MovieRepository movieRepository,
                        MoviePagingAndSortingRepository moviePagingAndSortingRepository) {
        this.movieRepository = movieRepository;
        this.moviePagingAndSortingRepository=moviePagingAndSortingRepository;
    }

    @Override
    public Movie addMovie(MovieDTO movieDTO) {
        Optional<Movie> optionalMovie = Optional.ofNullable(movieRepository.findByMovieName(movieDTO.getMovieName()));
        if (optionalMovie.isPresent()) {
            throw new MovieAlreadyExists("Movie already exists!");
        }
        //1. Create the Movie Entity and set the attributes
        Movie movie = new Movie();
        movie.setMovieName(movieDTO.getMovieName());
        movie.setDuration(movieDTO.getDuration());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setLanguage(movieDTO.getLanguage());
        movie.setGenre(movieDTO.getGenre());
        movie.setRating(movieDTO.getRating());

        //2 save this entity to DB
        movie=movieRepository.save(movie);
        return movie;
    }

    @Override
    public Movie getMovieByName(String movieName){
        Optional<Movie> optionalMovie= Optional.ofNullable(movieRepository.findByMovieName(movieName));
        if(optionalMovie.isEmpty()){
            throw new MovieNotFoundException("Error...Movie not found!");
        }
        Movie movie=optionalMovie.get();
        return movie;

    }

    @Override
    public List<Movie> getAllMovie() {
        List<Movie> movieList=movieRepository.findAll();
        return movieList;
    }

    @Override
    public Movie updateMovieByMovieName(String movieName,MovieDTO updateMovieDto) {

       //get movie by movie Name
        Optional<Movie> optionalMovie= Optional.ofNullable(movieRepository.findByMovieName(movieName));
        if(optionalMovie.isEmpty()){
            throw new MovieNotFoundException("Error...Movie not found!");
        }
        //get Movie from DB
        Movie movie=optionalMovie.get();

        //update movie fields with updatedMovieDto(mapped)
        movie.setMovieName(updateMovieDto.getMovieName());
        movie.setDuration(updateMovieDto.getDuration());
        movie.setReleaseDate(updateMovieDto.getReleaseDate());
        movie.setLanguage(updateMovieDto.getLanguage());
        movie.setGenre(updateMovieDto.getGenre());
        movie.setRating(updateMovieDto.getRating());

        //save updated movieDto to Databases
        movie=movieRepository.save(movie);
        return movie;

    }

    @Override
    public String deleteMovieById(Integer id){
        //1 find movie in DB
        Optional<Movie> optionalMovie=movieRepository.findById(id);

        //if not found throw Exception
        if(optionalMovie.isEmpty()){
            throw new MovieNotFoundException("Movie not found with given Id:"+id);
        }

        //if found get movie OBJ
        Movie movie=optionalMovie.get();

        //delete it
        movieRepository.delete(movie);

        return "Movie deleted Successfully with id="+id;
    }

    public Iterable<Movie> getMovieWithPagination(Integer page,Integer size){
        return moviePagingAndSortingRepository.findAll(PageRequest.of(page,size));
    }

}
