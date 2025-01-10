package com.BookMyShowJan2025.BookMyShow.Services;

import com.BookMyShowJan2025.BookMyShow.DTOs.TheaterDto;
import com.BookMyShowJan2025.BookMyShow.Exceptions.TheaterAlreadyExistsException;
import com.BookMyShowJan2025.BookMyShow.Exceptions.TheaterNotFoundException;
import com.BookMyShowJan2025.BookMyShow.Interfaces.TheaterInterface;
import com.BookMyShowJan2025.BookMyShow.Models.Theater;
import com.BookMyShowJan2025.BookMyShow.Repositories.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TheaterService implements TheaterInterface {

    private final TheaterRepository theaterRepository;
    @Autowired
    TheaterService(TheaterRepository theaterRepository){
        this.theaterRepository=theaterRepository;
    }

    @Override
    public Theater addTheater(TheaterDto theaterDto)  {
        //check if a Theater already exists with the same name and address
        Optional<Theater> optionalTheater= Optional.ofNullable(theaterRepository.findByTheaterNameAndAddress(theaterDto.getTheaterName(), theaterDto.getAddress()));
        if(!optionalTheater.isEmpty()){
            throw new TheaterAlreadyExistsException("Theater already Exists With the name:"+theaterDto.getTheaterName()+" ans address:"+theaterDto.getAddress());
        }

//       make theater entity with theaterDTo Builder is not working bcz lombok new version is having errors
//        Theater theater=Theater.builder().theaterName(theaterDto.getName())
//                .address(theaterDto.getAddress())
//                .noOfScreen(theaterDto.getNoOfScreen())
//                .build();

        Theater theater=new Theater();
        theater.setTheaterName(theaterDto.getTheaterName());
        theater.setAddress(theaterDto.getAddress());
        theater.setNoOfScreen(theaterDto.getNoOfScreen());
        //save it into DB
        theater=theaterRepository.save(theater);
        return theater;
    }

    @Override
    public String deleteTheaterById(Integer theaterId)  {
        //get theater by id
        Optional<Theater> optionalTheater=theaterRepository.findById(theaterId);
        if(optionalTheater.isEmpty()){
            throw new TheaterNotFoundException("Theater not found with id:"+theaterId);
        }

        //if found get theater Entity
        Theater theater=optionalTheater.get();

        //delete from DB
        theaterRepository.delete(theater);
        return "Theater has been deleted successfully!";
    }
}
