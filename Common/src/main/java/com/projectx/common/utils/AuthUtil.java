package com.projectx.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class AuthUtil {
    // need to move secret to application.properties for better security
    private final String SECRET_KEY = "your_secret_key_here";
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 saat

    // generate JWT token for a given username but isn't better to generate for user ID ? 
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>(); // claims is used to store additional information about the user
        return createToken(claims, username);  // createToken method is used to create a JWT token
    }

    // With builder pattern we can create a JWT token 
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }


    // Extract any claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extract username from token
     public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    // Check if token is expired
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        // Parameter works as taking a Claims object and execute getExpiration method , same as lambda
        return extractClaim(token, Claims::getExpiration);
    }

}
