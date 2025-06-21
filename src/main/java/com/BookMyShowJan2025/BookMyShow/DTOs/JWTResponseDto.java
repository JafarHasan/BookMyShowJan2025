package com.BookMyShowJan2025.BookMyShow.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTResponseDto {
    private String token;
    private String role;

    //getters and setters

}
