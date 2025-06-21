package com.BookMyShowJan2025.BookMyShow.Util;

import lombok.Value;

import java.util.Date;

public class JwtUtil {
    @Value("${jwt.secret}") // defined in application.properties
    private String secret;

    @Value("${jwt.expiration}") // e.g., 2592000000 for 30 days
    private long expirationMs;

    // Generate token
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    // Extract email (subject)
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    // Extract role
    public String extractRole(String token) {
        return (String) getClaims(token).get("role");
    }

    // Validate token
    public boolean isTokenValid(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // Parse claims
    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
