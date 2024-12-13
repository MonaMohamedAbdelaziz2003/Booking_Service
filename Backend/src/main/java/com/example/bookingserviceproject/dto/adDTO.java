package com.example.bookingserviceproject.dto;
import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class adDTO {

    private Long id;

    private String serviceName;

    private String description;
    
    
    private Double price;
    private MultipartFile img;

    private byte[] returnedImg;

    private long userid;

    private String companyName;

}
