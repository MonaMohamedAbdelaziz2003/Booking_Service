package com.example.bookingserviceproject.services.authantication;

// import java.security.MessageDigest;
// import java.security.NoSuchAlgorithmException;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// // @Configuration
// public class HashFun {

//  @Bean
//  public String hashSHA256(String message) throws NoSuchAlgorithmException {
//             MessageDigest digest = MessageDigest.getInstance("SHA-256");
//             byte[] hashBytes = digest.digest(message.getBytes());
//             StringBuilder hexString = new StringBuilder();
//             for (byte hashByte : hashBytes) {
//                 String hex = Integer.toHexString(0xff & hashByte);
//                 if (hex.length() == 1) hexString.append('0');
//                 hexString.append(hex);
//             }
//             return hexString.toString();
//     }
//  public String hashSHA256(String message) {
//         try {
//             MessageDigest digest = MessageDigest.getInstance("SHA-256");
//             byte[] hashBytes = digest.digest(message.getBytes());
//             StringBuilder hexString = new StringBuilder();
//             for (byte hashByte : hashBytes) {
//                 String hex = Integer.toHexString(0xff & hashByte);
//                 if (hex.length() == 1) hexString.append('0');
//                 hexString.append(hex);
//             }
//             return hexString.toString();
//         } catch (NoSuchAlgorithmException e) {
//             e.printStackTrace();
//             return null;
//         }
//     }
// }
