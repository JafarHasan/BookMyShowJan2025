package com.BookMyShowJan2025.BookMyShow.Models;

import com.BookMyShowJan2025.BookMyShow.Enum.SeatType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name="TheaterSeat")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TheaterSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seatId;

    @NotBlank(message = "Seat No can't be blank!")
    private String seatNo;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;


    @JoinColumn
    @ManyToOne      //many seats in one theater
    private Theater theater;

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public Theater getTheater() {
        return theater;
    }
    public void setTheater(Theater theater){
        this.theater=theater;
    }

}
