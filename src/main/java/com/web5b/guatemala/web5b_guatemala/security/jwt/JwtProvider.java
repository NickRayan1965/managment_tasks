package com.web5b.guatemala.web5b_guatemala.security.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration}")
  private int expiration;

  public String generateToken(UserDetails userDetails) {
    return Jwts.builder()
        .setSubject(userDetails.getUsername())
        .claim("authorities", userDetails.getAuthorities())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
        .signWith(getKey(secret))
        .compact();
  }

  private Key getKey(String secret) {
    byte[] decodedKey = Decoders.BASE64.decode(secret);
    return Keys.hmacShaKeyFor(decodedKey);
  }

  public Claims getClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(getKey(secret))
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  public String getSubject(String token) {
    return getClaims(token).getSubject();
  }

  public boolean validate(String token) {
    try {
      Jwts.parserBuilder()
          .setSigningKey(getKey(secret))
          .build()
          .parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
