package com.kadirgurturk.demo.security;

import com.kadirgurturk.demo.buisness.service.UserDetailsServer;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
@NoArgsConstructor
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
                UserDetails user = userDetailsServer.loadUserByUsername(email);
                if(user != null) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
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
