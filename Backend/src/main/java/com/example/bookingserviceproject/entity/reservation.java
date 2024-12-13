package com.example.bookingserviceproject.entity;
import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.bookingserviceproject.dto.ReservationDto;
import com.example.bookingserviceproject.dto.UserDto;
import com.example.bookingserviceproject.dto.adDTO;
import com.example.bookingserviceproject.enums.reservationStatus;
import com.example.bookingserviceproject.enums.reviewStauts;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity( name = "reservation")
public class reservation {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private reservationStatus reservationStatus;
    private reviewStauts reviewStauts;
    private Date bookData;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="users_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="company_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User company;

    
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="ads_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ad ad;


    public ReservationDto getDto() {
    ReservationDto dto=new ReservationDto();
        dto.setId(id);
        dto.setServiceName(ad.getServiceName());
        dto.setBookData(bookData);
        dto.setReservationStatus(reservationStatus);
        dto.setReviewStauts(reviewStauts);
        dto.setAdid(ad.getId());
        dto.setCompanyid(company.getId());
        dto.setUserName(user.getFirstname());
    return dto;
  }
}
