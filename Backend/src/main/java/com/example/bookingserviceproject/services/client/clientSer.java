package com.example.bookingserviceproject.services.client;

import java.util.List;

import com.example.bookingserviceproject.dto.ReservationDto;
import com.example.bookingserviceproject.dto.adDTO;
import com.example.bookingserviceproject.dto.adDetailsForClientDto;
import com.example.bookingserviceproject.dto.reviewDto;

public interface clientSer {
List<adDTO> getAllAds();
List<adDTO> getAdsByName(String name);
boolean bookService(ReservationDto reservationDto);
adDetailsForClientDto getDetailsForClientDto(Long adId);
List<ReservationDto> getAllBookingByUserId(Long id);
boolean giveReview(reviewDto reviewDto);
}
