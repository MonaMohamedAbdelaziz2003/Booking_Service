package com.example.bookingserviceproject.entity;
import lombok.Data;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.bookingserviceproject.dto.UserDto;
import com.example.bookingserviceproject.services.authantication.DES;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name= "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String phone;
    private String role;


    public static byte[] decoder(String data) {
        return Base64.getDecoder().decode(data);
     }

public UserDto getDto() throws Exception{
    UserDto userDto=new UserDto();
    userDto.setId(id);
    userDto.setFirstname(firstname);
    userDto.setLastname(lastname);
   userDto.setEmail(email);
   userDto.setPhone(phone);
   userDto.setPassword(password);
   userDto.setRole(role);
   return userDto;
}
// public UserDto getDto(SecretKey key) throws Exception{
    // DES des = new DES(key);
    // System.out.println("222222222222222"+key);
    // System.out.println("222222222222222"+firstname);
    // // System.out.println("333333333333333"+des.decryt(decoder(firstname)));
//    UserDto userDto=new UserDto();
//    DES des = new DES(key);
//    userDto.setId(id);
//    userDto.setFirstname(des.decryt(decoder(firstname)));
//    userDto.setLastname(des.decryt(decoder(lastname)));
//    userDto.setEmail(des.decryt(decoder(email)));
//    userDto.setPhone(des.decryt(decoder(phone)));
//    userDto.setPassword(password);
//    userDto.setRole(des.decryt(decoder(role)));
//    return userDto;
// }

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role));
}

@Override
public String getUsername() {
    return email;
}

@Override
public boolean isAccountNonExpired() {
   return true;
}
@Override
public boolean isAccountNonLocked() {
   return true;
}

@Override
public boolean isCredentialsNonExpired() {
    return true;
}

@Override
public boolean isEnabled() {
    return true;

}
public Object orElseThrow(Object object) {
    throw new UnsupportedOperationException("Unimplemented method 'orElseThrow'");
}
}
 