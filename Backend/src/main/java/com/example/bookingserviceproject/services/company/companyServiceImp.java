package com.example.bookingserviceproject.services.company;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bookingserviceproject.dto.ReservationDto;
import com.example.bookingserviceproject.dto.adDTO;
import com.example.bookingserviceproject.dto.reviewDto;
import com.example.bookingserviceproject.entity.Ad;
import com.example.bookingserviceproject.entity.Review;
import com.example.bookingserviceproject.entity.User;
import com.example.bookingserviceproject.entity.reservation;
import com.example.bookingserviceproject.enums.reservationStatus;
import com.example.bookingserviceproject.repository.adRepo;
import com.example.bookingserviceproject.repository.reversionRepo;
import com.example.bookingserviceproject.repository.reviewRepo;
import com.example.bookingserviceproject.repository.userRepo;

@Component
public class companyServiceImp implements companyService {

    @Autowired
    private userRepo userRepo;

    @Autowired
    private adRepo adRepo;
    @Autowired
    private reversionRepo reservRepo;
    @Autowired
    private reviewRepo reviewRepo;


    public boolean postAd(Long userId,adDTO adDTO) throws IOException{
        Optional<User> optionalUser=userRepo.findById(userId);
        if(optionalUser.isPresent()){
            Ad ad=new Ad();
            ad.setServiceName(adDTO.getServiceName());
            ad.setDescription(adDTO.getDescription());
            // ad.setImg(adDTO.getImg().getBytes());
            ad.setPrice(adDTO.getPrice());
            ad.setUser(optionalUser.get());
            adRepo.save(ad);
            return true;
        }
        return false;
    }
    public List<adDTO> getAllAds(Long userId){
        return adRepo.findAllByUserId(userId).stream().map(Ad::getDto).collect(Collectors.toList());
    }

     public List<reviewDto> getAllReviews(){
        return reviewRepo.findAll().stream().map(Review::getDto).collect(Collectors.toList());
    }

    public adDTO getAdById(Long id){
        Optional<Ad> ad=adRepo.findById(id);
        if(ad.isPresent()){
            return ad.get().getDto();
        }
        return null;

    }
    public boolean updateAdById(Long id,adDTO adDTO){
        Optional<Ad> ad=adRepo.findById(id);
        System.out.println(adDTO);
        if(ad.isPresent()){
            Ad Ad= ad.get();
            Ad.setServiceName(adDTO.getServiceName());
            Ad.setDescription(adDTO.getDescription());
            Ad.setPrice(adDTO.getPrice());
            // if(adDTO.getImg()!=null){
            //     Ad.setImg(adDTO.getImg().getBytes());
            // }
            adRepo.save(Ad);
            return true;
        }else{

            return false;
        }

    }


    public boolean deleteAd(Long id){
        Optional<Ad> ad=adRepo.findById(id);
        if(ad.isPresent()){
            adRepo.delete(ad.get());
            return true;
        }
            return false;
    }
    public boolean deletreview(Long id){
        Optional<Review> ad=reviewRepo.findById(id);
        if(ad.isPresent()){
            reviewRepo.delete(ad.get());
            return true;
        }
            return false;
    }

    public List<ReservationDto> AllBooking(Long companyId){
       return reservRepo.findAllByCompanyId(companyId).stream().map(reservation::getDto).collect(Collectors.toList());
    }

public boolean changeBookingStatus(Long boolkingId,String status){
    Optional<reservation> reservation=reservRepo.findById(boolkingId);
        if(reservation.isPresent()){
            reservation res=reservation.get();
            if(Objects.equals(status, "APPROVED")){
                res.setReservationStatus(reservationStatus.APPROVED);
            }else{
                res.setReservationStatus(reservationStatus.REJECTED);

            }
            reservRepo.save(res);
            return true;
        }
            return false;
}

}
