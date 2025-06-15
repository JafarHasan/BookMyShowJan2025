package com.BookMyShowJan2025.BookMyShow.Services;

import com.BookMyShowJan2025.BookMyShow.DTOs.TheaterDto;
import com.BookMyShowJan2025.BookMyShow.DTOs.TheaterSeatDto;
import com.BookMyShowJan2025.BookMyShow.DTOs.TheatreListDTO;
import com.BookMyShowJan2025.BookMyShow.Enum.SeatType;
import com.BookMyShowJan2025.BookMyShow.Exceptions.EmptyTheatreListException;
import com.BookMyShowJan2025.BookMyShow.Exceptions.TheaterAlreadyExistsException;
import com.BookMyShowJan2025.BookMyShow.Exceptions.TheaterNotFoundException;
import com.BookMyShowJan2025.BookMyShow.Interfaces.TheaterInterface;
import com.BookMyShowJan2025.BookMyShow.Models.Theater;
import com.BookMyShowJan2025.BookMyShow.Models.TheaterSeat;
import com.BookMyShowJan2025.BookMyShow.Repositories.TheaterRepository;
import com.BookMyShowJan2025.BookMyShow.Repositories.TheaterSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TheaterService implements TheaterInterface {

    private final TheaterRepository theaterRepository;
    private final TheaterSeatRepository theaterSeatRepository;
    @Autowired
    TheaterService(TheaterRepository theaterRepository,TheaterSeatRepository theaterSeatRepository){
        this.theaterRepository=theaterRepository;
        this.theaterSeatRepository=theaterSeatRepository;
    }

    @Override
    public Theater addTheater(TheaterDto theaterDto)  {
        //check if a Theater already exists with the same name and address
        Optional<Theater> optionalTheater= Optional.ofNullable(theaterRepository.findByTheaterNameAndAddress(theaterDto.getTheaterName(), theaterDto.getAddress()));
        if(!optionalTheater.isEmpty()){
            throw new TheaterAlreadyExistsException("Theater already Exists With the name:"+theaterDto.getTheaterName()+" at address:"+theaterDto.getAddress());
        }

//       make theater entity with theaterDTo Builder is not working bcz lombok new version is having errors
//        Theater theater=Theater.builder().theaterName(theaterDto.getTheaterName())
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

    public String associateTheaterWithSeat(TheaterSeatDto theaterSeatDto){
        //check if theater exists with give ID
        //get theater by id
        Integer theaterId=theaterSeatDto.getTheaterId();
        Optional<Theater> optionalTheater=theaterRepository.findById(theaterId);
        //if not found throw error
        if(optionalTheater.isEmpty()){
            throw new TheaterNotFoundException("Theater not found with id:"+theaterId);
        }
        //if found get theater Entity

        Theater theater=optionalTheater.get();
        //getting all theaterSeat List
        List<TheaterSeat> theaterSeatList=generateTheaterSeatList(theaterSeatDto,theater);

        //associate this with TheaterSeat
        //Add this to Theater Entity for mapping
        theater.setTheaterSeatList(theaterSeatList);

        //save Theater to DB
        theaterRepository.save(theater);

        //save all theaterSeat in DB
        theaterSeatRepository.saveAll(theaterSeatList);
        return "Theater Seats associated to theater_id="+theaterSeatDto.getTheaterId();
    }

    private List<TheaterSeat> generateTheaterSeatList(TheaterSeatDto theaterSeatDto,Theater theater){

        //make a list of all theaterSeat
        List<TheaterSeat> theaterSeatList=new ArrayList<>();

        Integer classicalSeat=theaterSeatDto.getNoOfClassicSeat();
        Integer premiumSeat=theaterSeatDto.getNoOfPremiumSeat();

        int noOfClassicalSeatsRows=classicalSeat/10;
        int noOfClassicalSeatsInLastRow=classicalSeat%10;

        int row;
        //here we are taking 10 rows by default
        for(row=1;row<=noOfClassicalSeatsRows;row++){
            for(int j=1;j<=10;j++){
                char ch=(char)('A'+j-1);
                String seatNo=""+row+ch;

                //map this DTO with TheaterSeat Entity
                TheaterSeat theaterSeat=new TheaterSeat();
                theaterSeat.setSeatNo(seatNo);
                theaterSeat.setSeatType(SeatType.CLASSIC);
                theaterSeat.setTheater(theater);

                //add this TheaterSeat to TheaterSeatList
                theaterSeatList.add(theaterSeat);

            }
        }
        //for the last row of Classic seat
        for(int j=1;j<=noOfClassicalSeatsInLastRow;j++){
            char ch=(char)('A'+j-1);
            String seatNo=""+row+ch;

            //map this DTO with TheaterSeat Entity
            TheaterSeat theaterSeat=new TheaterSeat();
            theaterSeat.setSeatNo(seatNo);
            theaterSeat.setSeatType(SeatType.CLASSIC);
            theaterSeat.setTheater(theater);

            //add this TheaterSeat to TheaterSeatList
            theaterSeatList.add(theaterSeat);

        }
        //Same logic for Premium Seats

        Integer noOfPremiumSeatsRow=premiumSeat/10;
        Integer noOfPremiumSeatsInLastRow=premiumSeat%10;
        int currentRow=row;
        if(noOfClassicalSeatsInLastRow>0){
            currentRow++;
        }

        for(row=currentRow;row<=noOfPremiumSeatsRow+currentRow-1;row++){
            for(int j=1;j<=10;j++){
                char ch=(char)('A'+j-1);
                String seatNo=""+row+ch;

                //map this DTO with TheaterSeat Entity
                TheaterSeat theaterSeat=new TheaterSeat();
                theaterSeat.setSeatNo(seatNo);
                theaterSeat.setSeatType(SeatType.PREMIUM);
                theaterSeat.setTheater(theater);

                theaterSeatList.add(theaterSeat);
            }
        }

        //for the last row
        for(int j=1;j<=noOfPremiumSeatsInLastRow;j++){
            char ch=(char)('A'+j-1);
            String seatNo=""+row+ch;

            //map this DTO with TheaterSeat Entity
            TheaterSeat theaterSeat=new TheaterSeat();
            theaterSeat.setSeatNo(seatNo);
            theaterSeat.setSeatType(SeatType.PREMIUM);
            //mapping with theater
            theaterSeat.setTheater(theater);
            theaterSeatList.add(theaterSeat);
        }
        return theaterSeatList;
    }
    public List<TheatreListDTO> getTheaterByLocation(String location){
        List<Theater> theaterList=theaterRepository.findAll();
        if(theaterList.isEmpty()){
            throw  new EmptyTheatreListException("Empty Theatre List");
        }
        List<TheatreListDTO> theatreListDTOS=new ArrayList<>();
        for(Theater t:theaterList){
            if(t.getAddress().toLowerCase().contains(location.toLowerCase())) {
                TheatreListDTO th = new TheatreListDTO();
                th.setTheaterId(t.getTheaterId());
                th.setAddress(t.getAddress());
                th.setTheaterName(t.getTheaterName());
                th.setNoOfScreen(t.getNoOfScreen());

                theatreListDTOS.add(th);
            }
        }
        if(theatreListDTOS.isEmpty()){
            throw new EmptyTheatreListException("No Theatre Found on this location...");
        }
        return theatreListDTOS;
    }
    public List<TheatreListDTO> getAllTheatre(){
        List<Theater> theaterList=theaterRepository.findAll();
        if(theaterList.isEmpty()){
            throw  new EmptyTheatreListException("Empty Theatre List");
        }
        List<TheatreListDTO> theatreListDTOS=new ArrayList<>();
        for(Theater t:theaterList){
            TheatreListDTO th=new TheatreListDTO();
            th.setTheaterId(t.getTheaterId());
            th.setAddress(t.getAddress());
            th.setTheaterName(t.getTheaterName());
            th.setNoOfScreen(t.getNoOfScreen());

            theatreListDTOS.add(th);
        }
        return theatreListDTOS;
    }

}
