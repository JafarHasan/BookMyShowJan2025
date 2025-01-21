package com.BookMyShowJan2025.BookMyShow.Interfaces;

import com.BookMyShowJan2025.BookMyShow.DTOs.UserDto;
import com.BookMyShowJan2025.BookMyShow.Models.User;

public interface UserInterface {

    User addUser(UserDto userDto) throws Exception;

}
