package com.BookMyShowJan2025.BookMyShow.Models;

import com.BookMyShowJan2025.BookMyShow.Enum.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="showSeat")
@Entity
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer showSeatId;

    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private boolean isBooked;

    private boolean isFoodAttached;

    @JoinColumn
    @ManyToOne
    private Show show;

    public Integer getShowSeatId() {
        return showSeatId;
    }

    public void setShowSeatId(Integer showSeatId) {
        this.showSeatId = showSeatId;
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

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public boolean isFoodAttached() {
        return isFoodAttached;
    }

    public void setFoodAttached(boolean foodAttached) {
        isFoodAttached = foodAttached;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }
}
