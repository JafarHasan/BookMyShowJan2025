package com.BookMyShowJan2025.BookMyShow.Models;

import com.BookMyShowJan2025.BookMyShow.Enum.SeatType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="TheaterSeat")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TheaterSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer theaterId;

    @NotBlank(message = "Seat No can't be blank!")
    private String seatNo;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @JoinColumn
    @ManyToOne
    private Theater theater;
}
