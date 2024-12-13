package com.example.bookingserviceproject.services.jwt;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
@Service
public interface userService {
    UserDetailsService userDetailsService();
}
