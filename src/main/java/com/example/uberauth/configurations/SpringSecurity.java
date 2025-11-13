package com.example.uberauth.configurations;


import com.example.uberauth.Repository.PassengerRepository;
import com.example.uberauth.filters.JwtAuthFilter;
import com.example.uberauth.services.UserDetailServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.List;

/**
 * here in this file we have complete setup for spring security configurations>>>>
 */

@Configuration
public class SpringSecurity {


    PassengerRepository passengerRepository;

    public SpringSecurity(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;

    }
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailServicesImpl(passengerRepository);
    }

 // IT IS VERY IMPORTANT BEACUSE when we include spring security our all api beacome auth protected therefore by adding belo w
    //configuration we allow request to bypass the authentications>>>
 @Bean
 public SecurityFilterChain securityFilterChain(HttpSecurity http,JwtAuthFilter jwtAuthFilter) throws Exception {
     http
             .csrf(csrf -> csrf.disable())
             .cors(cors -> cors.configurationSource(request -> {
                 var corsConfig = new CorsConfiguration();
                 corsConfig.setAllowedOriginPatterns(List.of("*"));
                 corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                 corsConfig.setAllowCredentials(true);
                 corsConfig.setAllowedHeaders(List.of("*"));
                 return corsConfig;
             }))
             .authenticationProvider(authenticationProvider())
             .authorizeHttpRequests(auth -> auth
                     // PUBLIC ROUTES
                     .requestMatchers("/api/v1/auth/signup/**").permitAll()
                     .requestMatchers("/api/v1/auth/signin/**").permitAll()

                     // PROTECTED ROUTES
                     .requestMatchers("/api/v1/auth/validate").authenticated()

                     // CATCH ALL — ALWAYS LAST
                     .anyRequest().authenticated()
             )
        .    addFilterBefore(jwtAuthFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

     return http.build();
 }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * it is very important to give
     * configuratuion like this because there are multiple constructor of BcryptPasswordEncode
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider  authenticationProvider(){
            DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

            daoAuthenticationProvider.setUserDetailsService(userDetailsService());
            daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

            //since DAOAuthenticationProvider extends AuthnticationProvider and it is interface therefore upcasting we can send follow LIP;
              return daoAuthenticationProvider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration  authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOriginPatterns("*")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}

/**
 * spring security provides a variety of options for performing authentication.these options follows a simple contract
 * an authenticationProvider process authentication request and fully authenticated object with full credentials is returned.
 *
 * the standard and most common impelmentaion is DAOAuthenticationProvider which retreives the user Details
 * from a simple read only user DAO which is userDetailService.this userDetailService only has access to the ueername
 * in order to retrive the fully user entity.which is enough for most scenarios.
 *
 *
 *
 * for auth to happen there are multiple strategies out of which most common is DAOAuthenticationsProvider
 * and this DAOAuthenticationProvider works on userDetails object and who give this userDetails object spring security gives
 * us the userDetails object ans we convert the userDetail with wrapper of passenger and finally return AuthPassengerDetails
 * objects beacuse we want to authenticate our passenger object>>
 *
 * and access of this object by DAOAuthenticationProvider  is available by userDetailServices
 *
 * initally we have
  * EMAIl
 * PASSWORD
 *
 * both goes to my server and passengerRepository find passenger BY email and return the passenger wrapper userDetails and upcasted
 * AuthPassengerDetail
 *
 * and now we have password from db and password from request we can match both of them by authenticationProvider>>>
 *
 *
 * AuthenticationProvider is just a contract based interface and there are multiple strategeis that extends and implement that
 * interface out of which DAOAuthenticationProvider which authenticate based on username and password>>>
 *
 * but before authenticating it fetches userDetail object using userDetailService class using function
 * loadByUserName()
 *
 * and return fully authenticated object with full credentials>>>
 *
 * this authentication manager going to call your startegies using authenticationProvider which is interface of your
 * strategies>>>>
 *
 * FINAL FLOW->>>>>>
 *
 * i have Authentication object which is coming from spring and UsernamePasswordAuthenticationToken this object is also
 * coming from spring security and authenticationMangaer.authenticate() function internally call authenticatioProvider straegies
 * and on further DAOAuthenticationProvider calls userDetailsService to fetch userDetail object and perform authentication logic
 *
 *
 */
