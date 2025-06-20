package com.BookMyShowJan2025.BookMyShow.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalTime;

@Table(name="ticket")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ticketId;

    @NotNull(message = "Show Date must not be null")
    @FutureOrPresent(message = "Show date should not be a past Date")
    private LocalDate showDate;//from show entity data

    @NotNull(message = "Show Time must not be null")
    private LocalTime showTime; //from show entity data

    @NotBlank(message = "Movie name must not be blank")
    @Size(min=1,max=50,message = "Movie name Length must be between 1 and 50 characters.")
    private String movieName;//from movie Entity data

    @NotBlank(message = "Theater name must not be blank")
    @Size(min=1,max=50,message = "Theater name Length must be between 1 and 50 characters.")
    private String theaterName;//from theater Entity data

    @NotNull(message="Amount Must not be null")
    @Positive(message = "Amount Must be greater than zero")
    private Integer totalAmt;

    private String bookedSeats;

    public String getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(String bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    //Many ticket of one show
    @JoinColumn
    @ManyToOne
    private Show show;

    //many ticket booked by one user
    @ManyToOne
    @JoinColumn(name = "user_user_id", foreignKey = @ForeignKey(name = "FK_user_ticket"))
    @OnDelete(action = OnDeleteAction.CASCADE)  // Hibernate-specific
    private User user;


    // Getters and Setters
    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public LocalDate getShowDate() {
        return showDate;
    }

    public void setShowDate(LocalDate showDate) {
        this.showDate = showDate;
    }

    public LocalTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalTime showTime) {
        this.showTime = showTime;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public Integer getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(Integer totalAmt) {
        this.totalAmt = totalAmt;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }












}


/*
Assignment: Develop and Validate the Ticket Entity
Task 1: Define the Ticket Entity
Implement the Ticket class with appropriate validations for each field using Spring Boot annotations. Add the following constraints:

ticketId:
Must be auto-generated. Use a UUID or a specific generation strategy.
Must not be null.

showDate and showTime:
Must not be null.
showDate must not be a past date.

showTime must not be null.

movieName:
Must not be blank.
Length must be between 1 and 50 characters.

theaterName:
Must not be blank.
Length must be between 1 and 50 characters.

totalAmt:
Must not be null.
Must be a positive integer greater than zero.

bookedSeats:
Must not be blank.

Task 2: Implement CRUD Operations
Create RESTful APIs for the following operations:
Create a ticket.
Fetch all tickets.
Fetch a ticket by ID.
Update a ticket's details.
Delete a ticket.
* */