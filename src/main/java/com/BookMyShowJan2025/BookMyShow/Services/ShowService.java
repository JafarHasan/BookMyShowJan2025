package com.BookMyShowJan2025.BookMyShow.Services;

import com.BookMyShowJan2025.BookMyShow.DTOs.ShowDto;
import com.BookMyShowJan2025.BookMyShow.Exceptions.EmptyListException;
import com.BookMyShowJan2025.BookMyShow.Exceptions.MovieNotFoundException;
import com.BookMyShowJan2025.BookMyShow.Exceptions.ShowNotFoundException;
import com.BookMyShowJan2025.BookMyShow.Exceptions.TheaterNotFoundException;
import com.BookMyShowJan2025.BookMyShow.Interfaces.ShowInterface;
import com.BookMyShowJan2025.BookMyShow.Models.*;
import com.BookMyShowJan2025.BookMyShow.Repositories.MovieRepository;
import com.BookMyShowJan2025.BookMyShow.Repositories.ShowRepository;
import com.BookMyShowJan2025.BookMyShow.Repositories.ShowSeatRepository;
import com.BookMyShowJan2025.BookMyShow.Repositories.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService implements ShowInterface {
    private final MovieRepository movieRepository;
    private final TheaterRepository theaterRepository;
    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;

    @Autowired
    public ShowService(MovieRepository movieRepository,TheaterRepository theaterRepository,ShowRepository showRepository
    ,ShowSeatRepository showSeatRepository){
        this.movieRepository=movieRepository;
        this.theaterRepository=theaterRepository;
        this.showRepository=showRepository;
        this.showSeatRepository=showSeatRepository;
    }


    @Override
    public String addShow(ShowDto showDto)  {
        //Check movie exists or not using movieName
        String movieName=showDto.getMovieName();
        Optional<Movie> optionalMovie= Optional.ofNullable(movieRepository.findByMovieName(movieName));
        if(optionalMovie.isEmpty()){
            throw new MovieNotFoundException("Error...Movie Not found!");
        }
        //Get Movie
        Movie movie=optionalMovie.get();

        //2 find Theater by theater id
        Integer theaterId=showDto.getTheaterId();
        Optional<Theater> optionalTheater=theaterRepository.findById(theaterId);
        if(optionalTheater.isEmpty()){
            throw new TheaterNotFoundException("Theater not found with id:"+theaterId);
        }

        //if found get theater Entity
        Theater theater=optionalTheater.get();

        //map Dto to Show Entity
        Show show=new Show();
        show.setShowDate(showDto.getShowDate());
        show.setShowTime(showDto.getShowTime());
        show.setMovie(movie);
        show.setTheater(theater);

        //save this into DB
        show=showRepository.save(show);

        //set ShowSeat List along with it
        List<TheaterSeat> theaterSeatList=theater.getTheaterSeatList();
        if(theaterSeatList.isEmpty()){
            throw new EmptyListException("There is no Theater Seat associated with this Theater id:"+theaterId);
        }
        List<ShowSeat> showSeatList=new ArrayList<>();

        //Now i need to map theaterSeat List with ShowSeat List
        //need to prepare my ShowSeats from theaterSeat
        for(TheaterSeat theaterSeat:theaterSeatList){
            ShowSeat showSeatObj=new ShowSeat();
            showSeatObj.setSeatNo(theaterSeat.getSeatNo());
            showSeatObj.setSeatType(theaterSeat.getSeatType());
            showSeatObj.setBooked(Boolean.FALSE);
            showSeatObj.setFoodAttached(Boolean.FALSE);
            showSeatObj.setShow(show);

            showSeatList.add(showSeatObj);
        }

        //add this showSeat list in shows Entity
        show.setShowSeatList(showSeatList);

        //save this ShowSeat List to DB
        showSeatRepository.saveAll(showSeatList);

        return "Show added for movie:"+movieName+" in "+theater.getTheaterName()+" successfully!";



    }

    @Override
    public String deleteShow(Integer showId) {
        //get show by show Id
        Optional<Show> optionalShow=showRepository.findById(showId);
        if(optionalShow.isEmpty()){
            throw new ShowNotFoundException("Error...! Show Not found with id:"+showId);
        }
        //get show
        Show show=optionalShow.get();
        //delete from db
        showRepository.delete(show);
        return "Show has been removed !";
    }
}
