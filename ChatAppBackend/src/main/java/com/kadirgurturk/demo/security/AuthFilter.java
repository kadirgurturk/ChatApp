package com.kadirgurturk.demo.security;

import com.kadirgurturk.demo.buisness.service.UserDetailsServer;
import com.kadirgurturk.demo.exception.UserExcepiton;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserDetailsServer userDetailsServer;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = requestToJwt(request);

        {
            // ---> We need to extract jwt value from HttpRequest, so we write a new function to extract it



            if(StringUtils.hasText(jwtToken) && jwtTokenProvider.ControlToken(jwtToken)) {
                String email = jwtTokenProvider.getEmailFromToken(jwtToken);
                if(email == null) throw new RuntimeException("Email is null");
                System.out.println("" + email);
                UserDetails user = userDetailsServer.loadUserByUsername(email);
                if(user != null) {
                    System.out.println(user.toString());
                    UsernamePasswordAuthenticationToken auth;

                    try {
                        auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    } catch (BadCredentialsException e) {
                        //Here is the error
                        throw new BadCredentialsException("Incorrect username or password", e);
                    }

                    try {
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                        System.out.println(auth.toString());
                } catch (BadCredentialsException e) {
                    // Kullanıcı doğrulaması başarısız oldu, bu durumu uygun şekilde işleyin.
                    throw new RuntimeException("kullnacı hatası");
                }
            }else{
                    throw new RuntimeException("This user is not valid an it's null");
                }

            }
        }
        filterChain.doFilter(request, response);
    }



    private String requestToJwt(HttpServletRequest request) {


        String bearer = request.getHeader("Authorization");
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {

            return bearer.substring(7);

        }
        return null;

    }
}
