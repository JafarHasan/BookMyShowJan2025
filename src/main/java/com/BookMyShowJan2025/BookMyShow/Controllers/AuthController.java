package com.BookMyShowJan2025.BookMyShow.Controllers;

import com.BookMyShowJan2025.BookMyShow.DTOs.JWTResponseDto;
import com.BookMyShowJan2025.BookMyShow.DTOs.LoginDto;
import com.BookMyShowJan2025.BookMyShow.DTOs.UserDto;
import com.BookMyShowJan2025.BookMyShow.Models.User;
import com.BookMyShowJan2025.BookMyShow.Services.AuthService;
import com.BookMyShowJan2025.BookMyShow.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    @Autowired
    public AuthController(AuthService authService){
        this.authService=authService;
    }
    //THIS API WILL ADD A NEW USER TO DB AS (addUSEr) using all the data of user from frontend through form
    //http://localhost:8080/auth/signup
    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody UserDto dto) {
        return new ResponseEntity<>(authService.signup(dto), HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<JWTResponseDto> login(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(authService.login(loginDto),HttpStatus.OK);
    }


}
