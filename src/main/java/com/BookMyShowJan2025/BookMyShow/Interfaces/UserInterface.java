package com.BookMyShowJan2025.BookMyShow.Interfaces;

import com.BookMyShowJan2025.BookMyShow.DTOs.UserDto;
import com.BookMyShowJan2025.BookMyShow.Models.User;

import java.security.spec.ECField;

public interface UserInterface {

    //User addUser(UserDto userDto) throws Exception;

    User getUserById(Integer userId) throws Exception;

    User getUserByEmail(String email)throws Exception;

}
