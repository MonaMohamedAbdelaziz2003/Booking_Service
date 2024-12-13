package com.example.bookingserviceproject.services.client;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookingserviceproject.dto.ReservationDto;
import com.example.bookingserviceproject.dto.adDTO;
import com.example.bookingserviceproject.dto.adDetailsForClientDto;
import com.example.bookingserviceproject.dto.reviewDto;
import com.example.bookingserviceproject.entity.Ad;
import com.example.bookingserviceproject.entity.Review;
import com.example.bookingserviceproject.entity.User;
import com.example.bookingserviceproject.entity.reservation;
import com.example.bookingserviceproject.enums.reservationStatus;
import com.example.bookingserviceproject.enums.reviewStauts;
import com.example.bookingserviceproject.repository.adRepo;
import com.example.bookingserviceproject.repository.reversionRepo;
import com.example.bookingserviceproject.repository.reviewRepo;
import com.example.bookingserviceproject.repository.userRepo;

@Service
public class clientServImpl implements clientSer{
    @Autowired
    private adRepo adRepo;
    @Autowired
    private userRepo userRepo;
    @Autowired
    private reversionRepo reversionRepo;
    @Autowired
    private reviewRepo reviewRepo;



    public List<adDTO> getAllAds(){
        return adRepo.findAll().stream().map(Ad::getDto).collect(Collectors.toList());
    }
    
    public List<adDTO> getAdsByName(String name){
        return adRepo.findAllByServiceName(name).stream().map(Ad::getDto).collect(Collectors.toList());
    }


    public boolean bookService(ReservationDto reservationDto){
        Optional<Ad> optionalAd=adRepo.findById(reservationDto.getAdid());
        Optional<User> optionalUser=userRepo.findById(reservationDto.getUserid());
        System.out.println(optionalAd);
        System.out.println(optionalUser);
        if( optionalAd.isPresent()&& optionalUser.isPresent()){
            reservation reservation=new reservation();
            reservation.setBookData(reservationDto.getBookData());
            reservation.setReservationStatus(reservationStatus.PENDING);
            reservation.setReviewStauts(reviewStauts.FALSE);
            reservation.setAd(optionalAd.get());
            reservation.setUser(optionalUser.get());
            reservation.setCompany(optionalAd.get().getUser());
            reversionRepo.save(reservation);
            System.out.println(reservation);
            return true;
        }
        return false;
    }


    public adDetailsForClientDto getDetailsForClientDto(Long adId){
        Optional<Ad> optionalAd=adRepo.findById(adId);
        adDetailsForClientDto adDetailsForClientDto=new adDetailsForClientDto();
        if( optionalAd.isPresent()){
            adDetailsForClientDto.setAdDTO(optionalAd.get().getDto());
            List<Review> review=reviewRepo.findAllByAdId(adId);
            adDetailsForClientDto.setReviewDate(review.stream().map(Review::getDto).collect(Collectors.toList()));
        }
        return adDetailsForClientDto;
    }

    public List<ReservationDto> getAllBookingByUserId(Long id){
        return reversionRepo.findAllByUserId(id).stream().map(reservation::getDto).collect(Collectors.toList());
    }




    public boolean giveReview(reviewDto reviewDto){
        Optional<User> optionalUser=userRepo.findById(reviewDto.getUserId());
        Optional<reservation> optionalBooking=reversionRepo.findById(reviewDto.getBookId());
        
        if( optionalBooking.isPresent()&& optionalUser.isPresent()){
            Review review=new Review();
            review.setReviewDate(new Date());
            review.setReview(reviewDto.getReview());
            review.setRating(reviewDto.getRating());
            review.setUser(optionalUser.get());
            review.setAd(optionalBooking.get().getAd());
            reviewRepo.save(review);
            reservation book=optionalBooking.get();
            book.setReviewStauts(reviewStauts.TRUE);
            reversionRepo.save(book);

            return true;
        }
        return false;
    }


  


}
