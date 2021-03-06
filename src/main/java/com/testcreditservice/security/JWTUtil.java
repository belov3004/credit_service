package com.testcreditservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    public JWTUtil() {
    }

    public String generateToken(String username) {
        return Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + this.expiration)).signWith(SignatureAlgorithm.HS512, this.secret.getBytes()).compact();
    }

    public boolean tokenValidate(String token) {
        Claims claims = this.getClaims(token);
        if (claims != null) {
            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());
            return username != null && expirationDate != null && now.before(expirationDate);
        }

        return false;
    }

    public String getUsername(String token) {
        Claims claims = this.getClaims(token);
        return claims != null ? claims.getSubject() : null;
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(this.secret.getBytes()).parseClaimsJws(token).getBody();
        } catch (Exception var3) {
            return null;
        }
    }
}
