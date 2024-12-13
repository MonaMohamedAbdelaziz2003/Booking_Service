package com.example.bookingserviceproject.services.company;
import com.example.bookingserviceproject.dto.ReservationDto;
import com.example.bookingserviceproject.dto.adDTO;
import com.example.bookingserviceproject.dto.reviewDto;

import java.io.IOException;
import java.util.List;

public interface companyService {
    boolean postAd(Long userId,adDTO adDTO) throws IOException;
    List<adDTO> getAllAds(Long userId);
    adDTO getAdById(Long id);
    boolean updateAdById(Long id,adDTO adDTO);
    boolean deleteAd(Long id);
    boolean deletreview(Long id);
    List<ReservationDto> AllBooking(Long companyId);
    boolean changeBookingStatus(Long boolkingId,String status);
     List<reviewDto> getAllReviews();
}
