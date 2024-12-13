package com.example.bookingserviceproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookingserviceproject.entity.reservation;

@Repository
public interface reversionRepo extends JpaRepository<reservation,Long> {
        List<reservation> findAllByCompanyId(Long companyId);
        List<reservation> findAllByUserId(Long userId);
}
