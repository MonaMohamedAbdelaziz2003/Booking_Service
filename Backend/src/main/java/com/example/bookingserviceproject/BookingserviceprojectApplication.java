package com.example.bookingserviceproject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.bookingserviceproject"})
public class BookingserviceprojectApplication {
	public static void main(String[] args) {
		SpringApplication.run(BookingserviceprojectApplication.class, args);
	}
}
