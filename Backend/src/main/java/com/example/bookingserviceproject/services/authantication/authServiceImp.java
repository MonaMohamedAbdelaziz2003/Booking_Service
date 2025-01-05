package com.example.bookingserviceproject.services.authantication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import com.example.bookingserviceproject.dto.UserDto;
import com.example.bookingserviceproject.dto.jwtAuthenticationRespo;
import com.example.bookingserviceproject.dto.signInRequest;
import com.example.bookingserviceproject.dto.signupRequistDTO;
import com.example.bookingserviceproject.repository.userRepo;
import com.example.bookingserviceproject.utill.JWTUtils;
import jakarta.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;
import com.example.bookingserviceproject.entity.User;
@Component
public class authServiceImp implements authService{

   @Autowired
   private userRepo userRepository ;
   

   
   @Autowired
   private AuthenticationManager authenticationManager ;

   public static String encode(byte[] data) {
      return Base64.getEncoder().encodeToString(data);
   }
    public String hashSHA256(String message) throws NoSuchAlgorithmException {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(message.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
    }


@Override
public UserDto signUpClient(signupRequistDTO signupRequist) throws Exception {
   // SecretKey key = DES.generateKey();
   // DES des = new DES(key);
   // user.setFirstname(encode(des.encrypt(signupRequist.getFirstname())));
   User user = new User();
   user.setFirstname(signupRequist.getFirstname());
   user.setLastname(signupRequist.getLastname());
   user.setPhone(signupRequist.getPhone());
   user.setEmail(signupRequist.getEmail());
   user.setRole("CLIENT");
   user.setPassword(hashSHA256(signupRequist.getPassword()));
   return userRepository.save(user).getDto();
}


@Override
public UserDto signUpCompany(signupRequistDTO signupRequist) throws Exception {
   // SecretKey key = DES.generateKey();
   // DES des = new DES(key);
   // user.setFirstname(encode(des.encrypt(signupRequist.getFirstname())));
      System.out.println("signupRequist");
      
      User user = new User();
      user.setFirstname(signupRequist.getFirstname());
      user.setLastname(signupRequist.getLastname());
      user.setPhone(signupRequist.getPhone());
       user.setRole(("COMPANY"));
       user.setEmail(signupRequist.getEmail());
       user.setPassword(hashSHA256(signupRequist.getPassword()));
         return userRepository.save(user).getDto();
 }

 

  @Override
  public Optional<User> presentEmail(String email){
   return userRepository.findFirstByEmail(email);
  }
  
  @Autowired
  private JWTUtils jwtUtils;

  
  public static final String TOKEN_PREFIX="Bearer";
  public static final String HEADER_STRING="Autharization";

  @Override
  public jwtAuthenticationRespo signIn(signInRequest signinRequest,HttpServletResponse response){

     authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(),signinRequest.getPassword()));
     var user = userRepository.findFirstByEmail(signinRequest.getEmail()).orElseThrow();
     jwtAuthenticationRespo jwtAuthenticationRespo=new jwtAuthenticationRespo();
         var jwt = jwtUtils.generateToken(user);
         // var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
         jwtAuthenticationRespo.setToken(jwt);
         // jwtAuthenticationRespo.setRefreshToken(refreshToken);
         response.addHeader("expose", "autharization");
         response.addHeader("allow", "autharization"+"ajkja");
         response.addHeader(HEADER_STRING,TOKEN_PREFIX + jwt);
         return jwtAuthenticationRespo;

  }
}

