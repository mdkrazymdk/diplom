package com.example.researchmonitoring.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    private static final String SECRET = "ОченьСекретныйИНаааамногоДлинНыйПарольДляJWT_123456";
    private static final long   EXP_MILLIS = 60 * 60 * 1000;     // 1 час
    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    /** создаём токен */
    public String generate(String username) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + EXP_MILLIS))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /** достаём subject = username */
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    /** true — токен валиден и не просрочен */
    public boolean validate(String token) {
        try { getClaims(token); return true; }
        catch (JwtException | IllegalArgumentException ex) {
            log.warn("invalid jwt: {}", ex.getMessage());
            return false;
        }
    }

    /* --------- private --------- */
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
