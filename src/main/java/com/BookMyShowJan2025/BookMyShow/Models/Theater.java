package com.BookMyShowJan2025.BookMyShow.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name="Theater")
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer theaterId;

    @NotBlank(message = "Theater Name must not be blank!")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String theaterName;

    @NotBlank(message = "Theater address must not be blank!")
    @Size(min = 10, max = 100, message = "Address should be between 10 and 100 characters")
    private String address;

    @NotNull(message = "No of screen shouldn't be null")
    @Min(value=1,message="Screen no should be at least 1")
    @Max(value=20,message="Screen no should be at max 20")
    private Integer noOfScreen;

    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
    private List<TheaterSeat> theaterSeatList=new ArrayList<>();

    public Integer getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(Integer theaterId) {
        this.theaterId = theaterId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public Integer getNoOfScreen() {
        return noOfScreen;
    }

    public void setNoOfScreen(Integer noOfScreen) {
        this.noOfScreen = noOfScreen;
    }

    public List<TheaterSeat> getTheaterSeatList() {
        return theaterSeatList;
    }

    public void setTheaterSeatList(List<TheaterSeat> theaterSeatList) {
        this.theaterSeatList = theaterSeatList;
    }
}

/*
* Assignment
Create an Entity class for Theater:

The entity should have fields theaterId, name, address, and noOfScreen.
Apply appropriate validation constraints using jakarta.validation annotations.
Provide meaningful error messages for invalid inputs.
Validation Requirements:

theaterId:
Should be auto-generated (no validation needed but mark it with @Id and @GeneratedValue annotations).
name:
Must not be blank.
Minimum length: 2 characters.
Maximum length: 50 characters.
address:
Must not be blank.
Minimum length: 10 characters.
Maximum length: 100 characters.
noOfScreen:
Must be a positive integer.
Minimum: 1 screen.
Maximum: 20 screens.
Create a REST Controller:

Implement an endpoint to add a new theater.
Use @Valid in the controller method to trigger validation automatically.
Handle Validation Errors:

*
*
@Min and @Max annotations:
These annotations are only applicable to numeric types (int, long, etc.).
For String fields like theaterName and address, use @Size to specify the minimum and maximum length instead.
@Size annotation:
Applied to theaterName and address to enforce character length constraints.
@NotBlank ensures that the field is not empty and doesn't contain only whitespace.
@NotNull annotation for noOfScreen:

Ensures the value is provided (cannot be null).
@Min and @Max ensure the value is within the required range.

Use @ControllerAdvice and @ExceptionHandler to catch validation errors and return user-friendly error responses.*/