package com.BookMyShowJan2025.BookMyShow.Controllers;

import com.BookMyShowJan2025.BookMyShow.DTOs.UserDto;
import com.BookMyShowJan2025.BookMyShow.Models.User;
import com.BookMyShowJan2025.BookMyShow.Repositories.UserRepositories;
import com.BookMyShowJan2025.BookMyShow.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }
    //http://localhost:8080/user/add
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody UserDto userDto){
        User user=userService.addUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
