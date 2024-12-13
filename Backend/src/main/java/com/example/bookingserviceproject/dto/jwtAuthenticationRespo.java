package com.example.bookingserviceproject.dto;
import lombok.Data;

@Data
public class jwtAuthenticationRespo {
private String token;
private String refreshToken;
}
