package com.example.bookingserviceproject.entity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.example.bookingserviceproject.dto.adDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


import lombok.Data;
@Data
@Entity
@Table(name="ads")
public class Ad {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String serviceName;

    private String description;

    private Double price;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

    @ManyToOne(fetch = FetchType.LAZY ,optional = false)
    @JoinColumn(name="users_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    
public adDTO getDto(){
   adDTO adDto=new adDTO();
   adDto.setId(id);
   adDto.setServiceName(serviceName);
   adDto.setDescription(description);
   adDto.setPrice(price);
   adDto.setCompanyName(user.getFirstname());
   adDto.setReturnedImg(img);
   return adDto;
}

}
