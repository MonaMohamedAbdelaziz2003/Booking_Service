package com.example.bookingserviceproject.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.bookingserviceproject.entity.Ad;


@Repository
public interface adRepo extends JpaRepository< Ad, Long >{
    List<Ad> findAllByUserId(Long userId);
    List<Ad> findAllByServiceName(String name);
}
