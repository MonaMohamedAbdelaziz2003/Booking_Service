package com.example.bookingserviceproject.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.example.bookingserviceproject.entity.User;

@Repository
public interface userRepo extends JpaRepository< User, Long >{
   Optional<User> findFirstByEmail(String email);
}

