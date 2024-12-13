package com.example.bookingserviceproject.entity;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.bookingserviceproject.dto.reviewDto;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Data
@Entity(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Date reviewDate;
    private String review;

    private Long rating;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="users_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="ads_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ad ad;


    public reviewDto getDto(){
        reviewDto reviewDto =new reviewDto();
        reviewDto.setId(id);
        reviewDto.setReview(review);
        reviewDto.setRating(rating);
        reviewDto.setReviewDate(reviewDate);
        reviewDto.setUserId(user.getId());
        reviewDto.setClientName(user.getFirstname());
        reviewDto.setAdId(ad.getId());
        reviewDto.setServiceName(ad.getServiceName());
        reviewDto.setEmail(user.getEmail());
        return reviewDto;
    }
}
