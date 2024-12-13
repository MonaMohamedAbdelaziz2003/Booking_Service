package com.example.bookingserviceproject.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookingserviceproject.dto.adDTO;
import com.example.bookingserviceproject.services.company.companyService;

import io.jsonwebtoken.io.IOException;

@RestController
@RequestMapping("/api/company")
public class companyController {
    @Autowired
    private companyService companyService;

    @PostMapping("ad/{userId}")
    public ResponseEntity<Object> postAd(@PathVariable Long userId,@ModelAttribute adDTO adDTO) throws IOException, java.io.IOException{
        boolean success=companyService.postAd(userId, adDTO);
        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
    }

}
