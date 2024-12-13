package com.example.bookingserviceproject.dto;


import java.util.List;

import lombok.Data;

@Data
public class adDetailsForClientDto {

    private adDTO adDTO;

    private List<reviewDto>  reviewDate;
}
