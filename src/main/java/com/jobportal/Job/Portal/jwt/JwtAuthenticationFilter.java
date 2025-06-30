package com.jobportal.Job.Portal.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtHelper jwtHelper;


    @Autowired
    private UserDetailsService userDetailsService;

    
    //this is a method of OncePerRequestFilter
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        //Authorization

        String requestHeader = request.getHeader("Authorization"); //get the token from header
        //Bearer 2352345235sdfrsfgsdfsdf
        // logger.info(" Header :  {}", requestHeader);
        String username = null;
        String token = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer")) // jwt token starts with bearer 
            //looking good
        {
            token = requestHeader.substring(7);//bearer k baad token starts
            try {

                username = this.jwtHelper.getUsernameFromToken(token); //token mese username ko get krenge aur username me assign krenge

            } catch (IllegalArgumentException e) {
                logger.info("Illegal Argument while fetching the username !!");
                e.printStackTrace();
            } catch (ExpiredJwtException e) {
                logger.info("Given jwt token is expired !!");
                e.printStackTrace();
            } catch (MalformedJwtException e) {
                logger.info("Some changed has done in token !! Invalid Token");
                e.printStackTrace();
            } catch (Exception e) {//koi bhi exception aae to....
                e.printStackTrace();

            }


        } else {
            logger.info("Invalid Header Value !! "); //if token does not start with bearer
        }


        //
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {


            //fetch user detail from username
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            Boolean validateToken = this.jwtHelper.validateToken(token, userDetails.getUsername());//we'll validate the token
            if (validateToken) { //agar token validate hua hai

                //set the authentication
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);


            } else {
                logger.info("Validation fails !!");
            }


        }

        filterChain.doFilter(request, response); //this will filter, agar authentication hua h then accept request else reject request


    }
}

