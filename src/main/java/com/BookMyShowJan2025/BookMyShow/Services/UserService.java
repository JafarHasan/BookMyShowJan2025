package com.BookMyShowJan2025.BookMyShow.Services;

import com.BookMyShowJan2025.BookMyShow.DTOs.UserDto;
import com.BookMyShowJan2025.BookMyShow.Enum.Role;
import com.BookMyShowJan2025.BookMyShow.Exceptions.UserNotFoundException;
import com.BookMyShowJan2025.BookMyShow.Interfaces.UserInterface;
import com.BookMyShowJan2025.BookMyShow.Models.User;
import com.BookMyShowJan2025.BookMyShow.Repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService implements UserInterface {

    private final PasswordEncoder passwordEncoder;
    private final UserRepositories userRepositories;
    @Autowired
    public UserService(UserRepositories userRepositories,PasswordEncoder passwordEncoder){
        this.userRepositories=userRepositories;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public User getUserById(Integer userId)  {
        //get user from DB
        Optional<User> optionalUser=userRepositories.findById(userId);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("Error!..invalid User ID");
        }

        //if find get user and return
        return optionalUser.get();
    }

    @Override
    public User getUserByEmail(String email) throws Exception {
        Optional<User> optionalUser=userRepositories.findByEmail(email);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User not found with this Email!");
        }
        return optionalUser.get();
    }


}
