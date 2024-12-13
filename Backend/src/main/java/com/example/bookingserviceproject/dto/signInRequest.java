package com.example.bookingserviceproject.dto;

import org.springframework.stereotype.Component;

import lombok.Data;
@Data
@Component
public class signInRequest {
 private String email;
 private String password;
}
