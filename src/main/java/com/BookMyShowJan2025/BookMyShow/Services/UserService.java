package com.BookMyShowJan2025.BookMyShow.Services;

import com.BookMyShowJan2025.BookMyShow.DTOs.UserDto;
import com.BookMyShowJan2025.BookMyShow.Exceptions.UserNotFoundException;
import com.BookMyShowJan2025.BookMyShow.Interfaces.UserInterface;
import com.BookMyShowJan2025.BookMyShow.Models.User;
import com.BookMyShowJan2025.BookMyShow.Repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserInterface {

    private final UserRepositories userRepositories;
    @Autowired
    public UserService(UserRepositories userRepositories){
        this.userRepositories=userRepositories;
    }

    @Override
    public User addUser(UserDto userDto)  {
        User user=new User();
        user.setUserName(userDto.getUserName());
        user.setAge(userDto.getAge());
        user.setMobileNo(userDto.getMobileNo());
        user.setEmail(userDto.getEmail());

        //save into DB
        user=userRepositories.save(user);
        return user;
    }

    @Override
    public User getUserById(Integer userId)  {
        //get user from DB
        Optional<User> optionalUser=userRepositories.findById(userId);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("Error!..invalid User ID");
        }

        //if find get user and return
        User user=optionalUser.get();
        return user;
    }


}
