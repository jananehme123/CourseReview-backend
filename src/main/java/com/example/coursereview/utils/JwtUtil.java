package com.example.coursereview.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {
    private static final String SECRET_KEY = "M5rRzhwqGOVCj58lR/sDaGj+a3fBh9TPLDxIYLT+klvv06/r3icnkMBi6AaOzfdTwTvXxbgAg/+z56k6wrrRqZIZ3aE/R8QSEji+HkRVHOqkcxjVGYUCjZvByxr4FgH/w0FstwOiQW353GPSzvi1yVi7sTvqSICAG5nDHzGgs0BY8DqBX0Yw9shN8H45wyLl4ID8fMD+lxvGehTb0BUUwOIAhlkioNEBkgOKKeBh2tG9ISuMu/X3uLHe2g0C/OKBap7sGPJt+TyVvYGX3gtZ42f+4m0dBSePoAhTl2veGBAa01GFyJEedSGHOrkbSZ1U69vCA+PvZ8prWj/dZkgjcGY0BzajAsWAcUh7fNIGG7w=";
    private final long EXPIRATION_TIME = 24  * 60 * 1000;

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    private Key getSecretKey() {
        byte[] decodedKey = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(decodedKey);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getSubject(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);

    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String getSubject(String jwt) {
       return extractClaim(jwt, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
