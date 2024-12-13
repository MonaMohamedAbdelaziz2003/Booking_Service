package com.example.bookingserviceproject.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.bookingserviceproject.dto.signInRequest;
import com.example.bookingserviceproject.dto.ReservationDto;
import com.example.bookingserviceproject.dto.UserDto;
import com.example.bookingserviceproject.dto.adDTO;
import com.example.bookingserviceproject.dto.reviewDto;
import com.example.bookingserviceproject.dto.signupRequistDTO;
import com.example.bookingserviceproject.entity.Review;
import com.example.bookingserviceproject.services.authantication.authService;
import com.example.bookingserviceproject.services.client.clientSer;
import com.example.bookingserviceproject.services.jwt.OurUserDetailsService;
import com.example.bookingserviceproject.utill.JWTUtils;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.servlet.http.HttpServletResponse;
import com.example.bookingserviceproject.services.company.companyService;
import com.example.bookingserviceproject.repository.reviewRepo;
import com.example.bookingserviceproject.repository.userRepo;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

@RestController
@RequestMapping("/api/v1/auth")
public class authanticationController {

   @Autowired
   private authService authserv;
   
   @Autowired
   private companyService companyService;

   @Autowired
   private userRepo userRepository;
  
   @Autowired
   private JWTUtils jwtUtils;
   @Autowired
   private OurUserDetailsService ourUserDetailsService ;
   public static final String TOKEN_PREFIX="Bearer";
   public static final String HEADER_STRING="Authorization";

  
   @PostMapping("/client/sign-up")
   public ResponseEntity<?> signupClient(@RequestBody signupRequistDTO requ) throws Exception{
       if (authserv.presentEmail(requ.getEmail()).isPresent()) {
           return new ResponseEntity<>("already exist", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto createUser = authserv.signUpClient(requ);
       return new ResponseEntity<>(createUser, HttpStatus.OK);
   }

   @PostMapping("/company/sign-up")
   public ResponseEntity<?> signupCompany(@RequestBody signupRequistDTO requ) throws Exception{//ResponseEntity<?>
       if (authserv.presentEmail(requ.getEmail()).isPresent()) {
           return new ResponseEntity<>("already exist", HttpStatus.NOT_ACCEPTABLE);
       }
       UserDto createUser = authserv.signUpCompany(requ);
       return new ResponseEntity<>(createUser, HttpStatus.OK);
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


   @PostMapping("/signin")
   public void signin(@RequestBody signInRequest signinRequest 
   ,HttpServletResponse response) throws IOException, JSONException, NoSuchAlgorithmException{
        //  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        // signinRequest.getEmail(),signinRequest.getPassword()));
        String password=hashSHA256(signinRequest.getPassword());
        var user = userRepository.findFirstByEmail(signinRequest.getEmail()).orElseThrow();
        System.out.println(user.getPassword()+"   " +password);
    
            if(user.getPassword().equals(password)){
                final UserDetails userDetails=ourUserDetailsService.loadUserByUsername(signinRequest.getEmail());
                var jwt = jwtUtils.generateToken(userDetails);
            response.getWriter().write(new JSONObject()
            .put("userId",user.getId())
            .put("role", user.getRole())
            .put("email", user.getEmail())
            .put("firstname", user.getFirstname())
            .put("lastname", user.getLastname())
            .toString()
            );
            response.addHeader("Access-Control-Expose-Headers", "Authorization");
            response.addHeader("Access-Control-Allow-Headers", "Authorization"+
            "X-PINGOTHER, Origin ,X-Requisted-With,Content-Type,Accept,X-Custom-header");
            response.addHeader(HEADER_STRING , TOKEN_PREFIX + jwt);
            }else{
                throw new IllegalArgumentException("Input cannot be null");
            }
       
   }

//////////////////////////////////////////////////////////////////// copany

    @PostMapping("/ad/{userId}")
    public ResponseEntity<Object> postAd(@PathVariable Long userId,@ModelAttribute adDTO adDTO) throws IOException{
        boolean success=companyService.postAd(userId, adDTO);
        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping("/ads/{userId}")
    public ResponseEntity<?> getAllAds(@PathVariable Long userId){
        return ResponseEntity.ok(companyService.getAllAds(userId));
    }
    @GetMapping("/ad/{adId}")
    public ResponseEntity<?> getAds(@PathVariable Long adId){
        adDTO adDTO= companyService.getAdById(adId);
        if(adDTO != null){
            return ResponseEntity.ok(adDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/ad/{adId}")
    public ResponseEntity<Object> updateAd(@PathVariable Long adId,@ModelAttribute adDTO adDTO) throws IOException{
        boolean success=companyService.updateAdById(adId, adDTO);
        // return ResponseEntity.ok(adDTO);
        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/ad/{adId}")
    public ResponseEntity<Object> deleteAd(@PathVariable Long adId) throws IOException{
        boolean success=companyService.deleteAd(adId);
        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<Object> deleteReview(@PathVariable Long reviewId) throws IOException{
        boolean success=companyService.deletreview(reviewId);
        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/Booking/{companyId}")
    public ResponseEntity<List<ReservationDto>> allAd(@PathVariable Long companyId){
        return ResponseEntity.ok(companyService.AllBooking(companyId));
    }

    @Autowired
    private reviewRepo reviewRepo;
   

    @GetMapping("/reviews")
    public ResponseEntity<List<reviewDto>> allReview(){
        return ResponseEntity.ok(companyService.getAllReviews());
    }


    @GetMapping("/booking/{bookingId}/{status}")
    public ResponseEntity<?> changeStatus(@PathVariable Long bookingId,@PathVariable String status) {
        boolean success=companyService.changeBookingStatus(bookingId,status);
        if(success)return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

//////////////////////////// client

    @Autowired
    private clientSer clientSer;

    @GetMapping("/ads")
    public ResponseEntity<?> getAds(){
            return ResponseEntity.ok(clientSer.getAllAds());
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> getAdsByName(@PathVariable String name){
        return ResponseEntity.ok(clientSer.getAdsByName(name));
    }

    @PostMapping("/book-service")
    public ResponseEntity<?> bookService(@RequestBody ReservationDto ReservationDto) throws IOException{
        boolean success=clientSer.bookService(ReservationDto);
        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping("/adById/{id}")
    public ResponseEntity<?> getAdsById(@PathVariable Long id){
        return ResponseEntity.ok(clientSer.getDetailsForClientDto(id));
    }

    @GetMapping("/myBooking/{id}")
    public ResponseEntity<?> getAllBookingByUserId(@PathVariable Long id){
        return ResponseEntity.ok(clientSer.getAllBookingByUserId(id));
    }

    @PostMapping("/review")
    public ResponseEntity<?> review(@RequestBody reviewDto reviewDto) throws IOException{
        boolean success=clientSer.giveReview(reviewDto);
        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
