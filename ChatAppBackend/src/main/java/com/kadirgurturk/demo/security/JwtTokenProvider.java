package com.kadirgurturk.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${chatapp.app.key}")
    private String APP_KEY;        //-----> This is a special key for our project

    @Value("${chatapp.ex}")
    private long EXPIRES_TIME;

    public String generateToken(Authentication auth)
    {

        JwtUserDetails userDetails = (JwtUserDetails) auth.getPrincipal(); //----> auth.getPrincipal function gives us to user who be to authentication.

        Date expiredDate = new Date(new Date().getTime() + EXPIRES_TIME); //------>

        return Jwts.builder()
                .setSubject(Long.toString(userDetails.getId())) //----> We gave user id as a string to token
                .setIssuedAt(new Date())   //----->  Token issue date
                .setExpiration(expiredDate)
                .signWith(key(),SignatureAlgorithm.HS256)
                .claim("email",auth.getName())
                .compact();
        //.signWith(SignatureAlgorithm.HS512, APP_KEY).compact(); // -------> We give a algorithm and key to create new token
    }



    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(APP_KEY));
    }

    public String getEmailFromToken(String token){ // ----> This is reverse method od generateToken. We take a token and parse it;
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()//---> we are starting to parse jwt
                .parseClaimsJws(token)
                .getBody();

        return String.valueOf(claims.get("email")); // ----> we return email
    }



    public boolean ControlToken(String token){
        // ---------> Gelen Token'nın doğru olup olmadğı ve geçerli olup olmadığına bakarız

        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;

    }

    private boolean isTokenExpired(String token) {
        //We checked here whether it has expired or not.
        Date expiration =  Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }

}
