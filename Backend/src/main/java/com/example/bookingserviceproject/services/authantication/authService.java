package com.example.bookingserviceproject.services.authantication;
import java.util.Optional;
import com.example.bookingserviceproject.entity.User;

import jakarta.servlet.http.HttpServletResponse;

import com.example.bookingserviceproject.dto.UserDto;
import com.example.bookingserviceproject.dto.jwtAuthenticationRespo;
import com.example.bookingserviceproject.dto.signInRequest;
import com.example.bookingserviceproject.dto.signupRequistDTO;

public interface authService {
   jwtAuthenticationRespo signIn(signInRequest signInRequest,HttpServletResponse response);
   UserDto signUpClient(signupRequistDTO signupRequist) throws Exception;
   UserDto signUpCompany(signupRequistDTO signupRequist) throws Exception; 
   Optional<User> presentEmail(String email);
}
