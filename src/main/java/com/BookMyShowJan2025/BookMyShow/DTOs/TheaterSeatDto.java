package com.BookMyShowJan2025.BookMyShow.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheaterSeatDto {

    private Integer theaterId;
    private Integer noOfPremiumSeat;
    private Integer noOfClassicSeat;

    public Integer getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(Integer theaterId) {
        this.theaterId = theaterId;
    }

    public Integer getNoOfPremiumSeat() {
        return noOfPremiumSeat;
    }

    public void setNoOfPremiumSeat(Integer noOfPremiumSeat) {
        this.noOfPremiumSeat = noOfPremiumSeat;
    }

    public Integer getNoOfClassicSeat() {
        return noOfClassicSeat;
    }

    public void setNoOfClassicSeat(Integer noOfClassicSeat) {
        this.noOfClassicSeat = noOfClassicSeat;
    }
}
