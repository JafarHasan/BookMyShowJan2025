package com.BookMyShowJan2025.BookMyShow.Models;

import com.BookMyShowJan2025.BookMyShow.Enum.Genre;
import com.BookMyShowJan2025.BookMyShow.Enum.Language;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="Movie")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @NotBlank(message = "Movie name can't be null")
    @Size(min = 2, message = "Movie name must be at least 2 characters long")
    private String movieName;

    @NotNull(message = "Movie duration can't be null")
    @Positive(message = "Duration must be a positive number")
    private Double duration;


    @NotNull(message = "Movie Date can't be null")
    private LocalDate releaseDate;


    @NotNull(message = "Rating must not be null")
    @Min(value = 0, message = "Rating must be at least 0")
    @Max(value = 10, message = "Rating can't exceed 10")
    private Double rating;

    @Enumerated(value = EnumType.STRING)
    @NotNull(message = "Language can't be null")
    @Column(length = 20)
    private Language language;

    @Enumerated(value = EnumType.STRING)
    @NotNull(message = "Genre can't be null")
    private Genre genre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}

