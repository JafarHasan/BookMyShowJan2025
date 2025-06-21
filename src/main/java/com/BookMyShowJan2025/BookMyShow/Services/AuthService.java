package com.BookMyShowJan2025.BookMyShow.Services;

import com.BookMyShowJan2025.BookMyShow.DTOs.JWTResponseDto;
import com.BookMyShowJan2025.BookMyShow.DTOs.LoginDto;
import com.BookMyShowJan2025.BookMyShow.DTOs.UserDto;
import com.BookMyShowJan2025.BookMyShow.Enum.Role;
import com.BookMyShowJan2025.BookMyShow.Exceptions.UserAlreadyExistsWithThisEmailException;
import com.BookMyShowJan2025.BookMyShow.Models.User;
import com.BookMyShowJan2025.BookMyShow.Repositories.UserRepositories;
import com.BookMyShowJan2025.BookMyShow.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepositories userRepositories;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    @Autowired
    public AuthService(UserRepositories userRepositories,PasswordEncoder passwordEncoder,AuthenticationManager authenticationManager,CustomUserDetailsService userDetailsService,JwtUtil jwtUtil){
        this.userRepositories=userRepositories;
        this.passwordEncoder=passwordEncoder;
        this.authenticationManager=authenticationManager;
        this.userDetailsService=userDetailsService;
        this.jwtUtil=jwtUtil;

    }


    public User signup(UserDto userDto) {
        //check if this user already created an ac using the same email
        Optional<User> optionalUser=userRepositories.findByEmail(userDto.getEmail());
        if(!optionalUser.isEmpty()){
            throw new UserAlreadyExistsWithThisEmailException("User Already Exists with the same Email");
        }
            User user=new User();
            user.setUserName(userDto.getUserName());
            user.setAge(userDto.getAge());
            user.setMobileNo(userDto.getMobileNo());
            user.setEmail(userDto.getEmail());

            // user.setPassword(userDto.getPassword());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));

            //BY DEFAUlT ROLE IS USER FOR ALL
            user.setRole(Role.USER);

            //CURR SYSTEM DATE TIME
            user.setLastLogin(LocalDateTime.now());
            //save into DB
            user=userRepositories.save(user);
            return user;
        }


    public JWTResponseDto login(LoginDto loginDto) {
            // Step 1: Authenticate user
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(), loginDto.getPassword()
                    )
            );

            // Step 2: Load UserDetails
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getEmail());

            // Step 3: Generate JWT
        String token = jwtUtil.generateToken(loginDto.getEmail());


        // Step 4: Extract role (first authority)
            String role = userDetails.getAuthorities().iterator().next().getAuthority();

            // Step 5: Return token and role
            JWTResponseDto jwtResponse = new JWTResponseDto();
            jwtResponse.setToken(token);
            jwtResponse.setRole(role);

            return jwtResponse;
        }
}
