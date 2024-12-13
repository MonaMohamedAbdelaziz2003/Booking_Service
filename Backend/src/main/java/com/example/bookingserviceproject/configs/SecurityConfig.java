package com.example.bookingserviceproject.configs;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import com.example.bookingserviceproject.enums.UserRole;
// // import com.example.bookingserviceproject.services.jwt.jwtRequestFilter;
// import com.example.bookingserviceproject.services.jwt.userService;
// import lombok.RequiredArgsConstructor;


import com.example.bookingserviceproject.services.jwt.JWTAuthFIlter;

// @Configuration
// @EnableWebSecurity
// public class webSecurityConfig<AuthanticationManager> {
//     @Autowired
//     private jwtRequestFilter requFilter;
//     @Autowired
//     private userService userService;

//     @Bean
//     public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception{
//          http.csrf(AbstractHttpConfigurer::disable)
//                 .authorizeHttpRequests((requist) ->requist.requestMatchers("/api/v1/auth/**")
//                 .permitAll()
//                 .requestMatchers("/api/v1/admin").hasAnyAuthority(UserRole.COMPANY.name())
//                 .requestMatchers("/api/v1/user").hasAnyAuthority(UserRole.CLIENT.name())
//                 .anyRequest().authenticated())
//             .sessionManagement((manager)->manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//             .authenticationProvider(authenticationProvider()).addFilterBefore(requFilter,UsernamePasswordAuthenticationFilter.class);
//             return http.build();
//     }
   
//     @Bean
//     public AuthenticationManager authanticationManager(AuthenticationConfiguration config) throws Exception{
//         return config.getAuthenticationManager();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder(){
//         return new BCryptPasswordEncoder();

//     }
//     @Bean
//     public AuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider authprovider=new DaoAuthenticationProvider();
//        authprovider.setUserDetailsService(userService.userDetailsService());
//        authprovider.setPasswordEncoder(passwordEncoder());
//        return authprovider;
//     }
// }

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private com.example.bookingserviceproject.services.jwt.OurUserDetailsService ourUserDetailsService;
    @Autowired
    private JWTAuthFIlter jwtAuthFIlter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(request -> request.requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/api/v1/admin").hasAnyAuthority("COMPANY")
                        .requestMatchers("/api/v1/user").hasAnyAuthority("CLIENT")
                        .requestMatchers("/adminuser/**").hasAnyAuthority("COMPANY", "CLIENT")
                        .requestMatchers("/signin/**").hasAnyAuthority("COMPANY", "CLIENT")
                        .anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                        jwtAuthFIlter, UsernamePasswordAuthenticationFilter.class
                );
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(ourUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
