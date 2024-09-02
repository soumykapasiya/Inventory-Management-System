package com.kapasiya.ims.inventorymanagementsystem.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Slf4j
@Component
public class JwtUtil {
    private static final String ROLES = "roles";

    @Value("${jwt.secret:#{null}}")
    private String secret;

    @Value("${jwt.token-validity}")
    private long tokenValidity;

    private Key key;
    private final Set<String> blackListedTokens = new HashSet<>();

    @PostConstruct
    public void init() {
        if (secret == null || secret.isEmpty()) {
            this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
            log.info("Generated new secret key");
        } else {
            // Ensure the secret is of a proper length for HMAC SHA-256
            this.key = Keys.hmacShaKeyFor(secret.getBytes());
            log.info("Loaded secret key from properties");
        }
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public List<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        Object roles = claims.get(ROLES);
        if (roles instanceof List<?>) {
            return (List<String>) roles;
        } else {
            return Collections.emptyList();
        }
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims =
                Map.of(ROLES, userDetails.getAuthorities().parallelStream()
                        .map(GrantedAuthority::getAuthority)
                        .toList());
        return createToken(claims, userDetails.getUsername());
    }

    public String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidity * 1000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = this.extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token) && !blackListedTokens.contains(token));
    }

    public void blackListToken(String token) {
        blackListedTokens.add(token);
    }

    public boolean isTokenBlackListed(String token) {
        return blackListedTokens.contains(token);
    }
}

