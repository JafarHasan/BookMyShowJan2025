package com.BookMyShowJan2025.BookMyShow.Controllers;

import com.BookMyShowJan2025.BookMyShow.DTOs.UserDto;
import com.BookMyShowJan2025.BookMyShow.Models.User;
import com.BookMyShowJan2025.BookMyShow.Repositories.UserRepositories;
import com.BookMyShowJan2025.BookMyShow.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    //This API will be use when User will sign-UP(create a new account)
    //http://localhost:8080/user/add
//    @PostMapping("/add")
//    public ResponseEntity<User> addUser(@RequestBody UserDto userDto){
//        User user=userService.addUser(userDto);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
    //IMPLEMENTED IN AUTHCONTROLLER

    //GET:http://localhost:8080/user/get-user-by-id/1
    @GetMapping("/get-user-by-id/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userId){
        return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
    }
}
