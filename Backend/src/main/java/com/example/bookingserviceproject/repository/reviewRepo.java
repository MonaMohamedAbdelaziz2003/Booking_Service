package com.example.bookingserviceproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookingserviceproject.entity.Review;

@Repository
public interface reviewRepo extends JpaRepository<Review,Long> {
    List<Review> findAllByAdId(Long adId);
    // List<Review> findAll();
}
