package com.example.bookingserviceproject.utill;
// import java.security.Key;
// import java.util.*;
// import java.util.function.Function;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.stereotype.Service;

// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.SignatureAlgorithm;
// import io.jsonwebtoken.io.Decoders;
// import io.jsonwebtoken.security.Keys;


// @Service
// public class jwtServiceImpl implements jwtService {
// private static final String SECRET = "MySecretKeyForJWTSigningAndVerification123!";


// @Override
// public String extractUsername(String token) {
//     return extractClaim(token,Claims::getSubject);
// }

// @Override
// public String generateToken(UserDetails user) {
//     return Jwts.builder()
//     .setSubject(user.getUsername())
//     .setIssuedAt(new Date(System.currentTimeMillis()))
//     .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
//     .signWith(getSignKey(),SignatureAlgorithm.HS512)
//     .compact();
// }

// @Override
// public String generateRefreshToken(Map<String,Object> extraClaims, UserDetails userDetails){
//     return Jwts.builder().setClaims(extraClaims)
//     .setIssuedAt(new Date(System.currentTimeMillis()))
//     .setExpiration(new Date(System.currentTimeMillis() + 604800000))
//     .signWith(getSignKey(),SignatureAlgorithm.HS512).compact();
// }

// @Override
// public boolean isTokenValidate(String token, UserDetails userDetails) {
//       return extractClaim(token,Claims::getExpiration).before(new Date());

// }


// private Key getSignKey(){
//     byte[] secretBytes = Decoders.BASE64.decode(SECRET);
//     return Keys.hmacShaKeyFor(secretBytes);
   
// }

// private Claims extractAllClaims(String token){
//     return Jwts.parserBuilder()
//     .setSigningKey(getSignKey())
//     .build()
//     .parseClaimsJws(token)
//     .getBody();
    
// }
// public<T> T extractClaim(String token,Function<Claims,T> claimsResolver){
//     final Claims claims = extractAllClaims(token);
//     return claimsResolver.apply(claims);
// }

// // private Boolean isTokenExpired(String token){
// //   return extractClaim(token,Claims::getExpiration).before(new Date());
// // }


// }

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTUtils {

    private SecretKey Key;
    private  static  final long EXPIRATION_TIME = 86400000; //24hours or 86400000 milisecs
    public JWTUtils(){
        String secreteString = "843567893696976453275974432697R634976R738467TR678T34865R6834R8763T478378637664538745673865783678548735687R3";
        byte[] keyBytes = Base64.getDecoder().decode(secreteString.getBytes(StandardCharsets.UTF_8));
        this.Key = new SecretKeySpec(keyBytes, "HmacSHA256");
    }
    
    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(Key)
                .compact();
    }
    public String generateRefreshToken(Map<String, Object> claims, UserDetails userDetails){
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(Key)
                .compact();
    }
    
    public String extractUsername(String token){
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction){
        return claimsTFunction.apply(Jwts.parser().verifyWith(Key).build().parseSignedClaims(token).getPayload());
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    public boolean isTokenExpired(String token){
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }

}
