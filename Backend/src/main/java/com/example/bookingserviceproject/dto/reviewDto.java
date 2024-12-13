package com.example.bookingserviceproject.dto;

import java.util.Date;

import lombok.Data;

@Data
public class reviewDto {

    private Long id;
    private Date reviewDate;
    private String review;
    private String clientName;
    private String serviceName;
    private String email;
    private Long rating;
    private Long userId;
    private Long adId;
    private Long bookId;



    
}
