package com.example.bookingserviceproject.dto;

import com.example.bookingserviceproject.enums.UserRole;

import lombok.Data;

@Data
public class signupRequistDTO {
   private Long id;
   private String email;
   private String password;
   private String firstname;
   private String lastname;
   private String phone;
   private UserRole role;
}
