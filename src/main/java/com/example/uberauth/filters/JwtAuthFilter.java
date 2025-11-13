package com.example.uberauth.filters;

import com.example.uberauth.services.JwtServices;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private JwtServices  jwtServices;
    private UserDetailsService userDetailsService;

    public JwtAuthFilter(JwtServices jwtServices,UserDetailsService userDetailsService) {
        this.jwtServices = jwtServices;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();

        // ✅ Skip authentication for signup and signin endpoints
        if (path.startsWith("/api/v1/auth/signup") || path.startsWith("/api/v1/auth/signin")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token=null;
        if(request.getCookies()!=null){
              for(Cookie cookie : request.getCookies()){
                   if(cookie.getName().equals("jwtToken")){
                      token=cookie.getValue();
                   }
              }
          }
        if(token==null){
            //user has not provided any jwt token hence request should not go forward>>
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        String email=jwtServices.extractEmail(token);

        if(email!=null){
            UserDetails userDetails=userDetailsService.loadUserByUsername(email);
            if(jwtServices.validateToken(token,userDetails.getUsername())){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails, null);
                usernamePasswordAuthenticationToken.setDetails(userDetails);

                //to hold the context for that entire request lifecycle so that we can access that>>>
                /**
                 * if we did not allow spring security to remember user detail by using spring context then
                 * we have to manually pass the user detail to all that function that required  in that request lifecycle
                 * or else we can use springContextHolder and if we have toi manually pass then we have to pass
                 * in the form of DTOs>>>>
                 * therefore we prefer to create Authentication object and store its context>>
                 *
                 * Each thread (each HTTP request) gets its own personal security context box 🧰
                 *
                 * to access the context object then
                 * SecurityContextHolder.getContext().getAuthentication()>>>
                 */
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        }
        filterChain.doFilter(request,response);

    }

}
