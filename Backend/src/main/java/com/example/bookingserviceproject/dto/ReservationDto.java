package com.example.bookingserviceproject.dto;


import org.springframework.web.multipart.MultipartFile;
import java.util.Date;
import com.example.bookingserviceproject.enums.*;

import lombok.Data;
@Data
public class ReservationDto {
 
    private Long id;

    
    private String serviceName;
    private reviewStauts reviewStauts;
    private reservationStatus reservationStatus;
    private Date bookData;

    private long userid;
    private long companyid;
    private long adid;

    private String userName;
}
