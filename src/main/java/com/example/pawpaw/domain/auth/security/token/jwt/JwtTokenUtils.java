package com.example.pawpaw.domain.auth.security.token.jwt;

import com.example.pawpaw.domain.auth.security.details.CustomUserDetails;
import com.example.pawpaw.domain.auth.security.details.CustomUserDetailsService;
import com.example.pawpaw.domain.auth.security.token.Token;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenUtils {

    private final Key key;
    private final long expirationTimeInMillis = 1000 * 60 * 60 * 1; // 2시간
    private final CustomUserDetailsService customUserDetailsService;

    public JwtTokenUtils(@Value("${jwt.secret}") String secretKey, CustomUserDetailsService customUserDetailsService) {
        byte[] keyBytes = secretKey.getBytes();
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.customUserDetailsService = customUserDetailsService;
    }

    public Token generateAccessToken(Integer user_id) {
        Date expiration = new Date(System.currentTimeMillis() + expirationTimeInMillis);
        String accessToken = Jwts.builder()
                .setSubject(String.valueOf(user_id))
                .claim("auth", "ROLE_USER")
                .setExpiration(expiration)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return new Token(accessToken, expiration);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException | ExpiredJwtException
                 | UnsupportedJwtException | IllegalArgumentException e) {
            log.error("Invalid JWT TokenResponseDTO. " + e.getMessage());
            return false;
        }
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);
        CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
