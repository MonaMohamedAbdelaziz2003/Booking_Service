package com.example.bookingserviceproject.dto;

import lombok.Data;

@Data
public class UserDto {
   private Long id;
   private String email;
   private String password;
   private String firstname;
   private String phone;
   private String lastname;
   private String role;
}
