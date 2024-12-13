package com.example.bookingserviceproject.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.bookingserviceproject.services.client.clientSer;

@RestController
@RequestMapping("/api/client")
public class clientController {

    @Autowired
    private clientSer clientSer;
    @GetMapping("/ads")
    public ResponseEntity<?> getAds(){
            return ResponseEntity.ok(clientSer.getAllAds());
    }

}
