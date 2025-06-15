package com.BookMyShowJan2025.BookMyShow.Services;

import com.BookMyShowJan2025.BookMyShow.DTOs.BookTicketDto;
import com.BookMyShowJan2025.BookMyShow.Enum.SeatType;
import com.BookMyShowJan2025.BookMyShow.Exceptions.ShowNotFoundException;
import com.BookMyShowJan2025.BookMyShow.Exceptions.TicketNotFoundException;
import com.BookMyShowJan2025.BookMyShow.Exceptions.UserNotFoundException;
import com.BookMyShowJan2025.BookMyShow.Interfaces.TicketInterface;
import com.BookMyShowJan2025.BookMyShow.Models.Show;
import com.BookMyShowJan2025.BookMyShow.Models.ShowSeat;
import com.BookMyShowJan2025.BookMyShow.Models.Ticket;
import com.BookMyShowJan2025.BookMyShow.Models.User;
import com.BookMyShowJan2025.BookMyShow.Repositories.ShowRepository;
import com.BookMyShowJan2025.BookMyShow.Repositories.ShowSeatRepository;
import com.BookMyShowJan2025.BookMyShow.Repositories.TicketRepository;
import com.BookMyShowJan2025.BookMyShow.Repositories.UserRepositories;
import com.BookMyShowJan2025.BookMyShow.Response.TicketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService implements TicketInterface {

    private final ShowRepository showRepository;
    private final UserRepositories userRepositories;
    private final ShowSeatRepository showSeatRepository;
    private final TicketRepository ticketRepository;
    private final JavaMailSender javaMailSender;
    @Autowired
    public TicketService(ShowRepository showRepository,
                         UserRepositories userRepositories,
                         ShowSeatRepository showSeatRepository,
                         TicketRepository ticketRepository,
                         JavaMailSender javaMailSender){
        this.showRepository=showRepository;
        this.userRepositories=userRepositories;
        this.showSeatRepository=showSeatRepository;
        this.ticketRepository=ticketRepository;
        this.javaMailSender=javaMailSender;
    }

    @Override
    public String bookTicket(BookTicketDto bookTicketDto) {

        //1 find show entity first
        Optional<Show> optionalShow=showRepository.findById(bookTicketDto.getShowId());
        if(optionalShow.isEmpty()){
            throw new ShowNotFoundException("Error...! Show Not found with id:"+bookTicketDto.getShowId());
        }

        //2 find User entity
        //get user from DB
        Optional<User> optionalUser=userRepositories.findById(bookTicketDto.getUserId());
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("Error!..invalid User ID");
        }

        //if found ,get Show and User entity
        Show show=optionalShow.get();
        User user=optionalUser.get();

        //get showSeatList and marked those seats as booked and calculate amount
        Integer totalAmount=0;
        List<String> bookedShowSeat=bookTicketDto.getRequestedSeats();

        //get ShowSeatList from show and update from DTO showSeat
        List<ShowSeat> showSeatList1=show.getShowSeatList();
        for(ShowSeat showSeat:showSeatList1){
            String seatNo=showSeat.getSeatNo();
            if(bookedShowSeat.contains(seatNo)){
                showSeat.setBooked(Boolean.TRUE);
                showSeat.setFoodAttached(Boolean.TRUE);

                //assuming classic seat charge is 100
                if(showSeat.getSeatType().equals(SeatType.CLASSIC)){
                    totalAmount+=100;
                }
                //Premium seat charged is 200;
                else if(showSeat.getSeatType().equals(SeatType.PREMIUM)){
                    totalAmount+=200;
                }
            }

        }

        //Create Ticket Entity and set the attribute
        Ticket ticket=new Ticket();
        ticket.setMovieName(show.getMovie().getMovieName());
        ticket.setShowDate(show.getShowDate());
        ticket.setShowTime(show.getShowTime());
        ticket.setTheaterName(show.getTheater().getTheaterName());
        ticket.setTotalAmt(totalAmount);
        ticket.setBookedSeats(bookTicketDto.getRequestedSeats().toString());
        ticket.setUser(user);
        ticket.setShow(show);

        //save seats to DB
        showSeatRepository.saveAll(showSeatList1);

        //save ticket to DB
        ticket=ticketRepository.save(ticket);
        return "Ticket booked Successfully,Ticket ID:"+ticket.getTicketId();
    }

    @Override
    public List<Ticket> getAllTickets() throws Exception {
        return List.of();
    }

    @Override
    public TicketResponse getTicketById(String ticketId) throws Exception {
        Optional<Ticket> optionalTicket=ticketRepository.findById(ticketId);
        if(optionalTicket.isEmpty()){
            throw new TicketNotFoundException("Invalid Ticket ID");
        }
        //get ticket entity
        Ticket ticket=optionalTicket.get();
        //map with Ticket Response
        TicketResponse ticketResponse=new TicketResponse();

        ticketResponse.setShowDate(ticket.getShowDate());
        ticketResponse.setShowTime(ticket.getShowTime());
        ticketResponse.setTheaterName(ticket.getTheaterName());
        ticketResponse.setMovieName(ticket.getMovieName());
        ticketResponse.setBookedSeats(ticket.getBookedSeats());
        ticketResponse.setTotalAmount(ticket.getTotalAmt());

        //now we need to send email of confirmation so
        //get user than by user get email
        User user=ticket.getUser();

        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setFrom("jafarhasan1510@gmail.com");
        mailMessage.setSubject("Ticket Confirmation");
        String body="Hello "+user.getUserName()+
                "\nThis is a confirmation email that your ticked has been booked in"+
                "\nFind the details below:"+
                "\nTheater Name:"+ticketResponse.getTheaterName()+
                "\nMovie Name:"+ticketResponse.getMovieName()+
                "\nShow Date:"+ticketResponse.getShowDate()+
                "\nShow Time:"+ticketResponse.getShowTime()+
                "\nTotal Amount:"+ticketResponse.getTotalAmount()+
                "\n"+"ThankYou!....";
        mailMessage.setText(body);
        javaMailSender.send(mailMessage);
        return ticketResponse;
    }

    @Override
    public TicketResponse updateTicket(String ticketId, BookTicketDto updatedTicketDto) throws Exception {
        return null;
    }

    @Override
    public String deleteTicket(String ticketId) throws Exception {
        return "";
    }


}
