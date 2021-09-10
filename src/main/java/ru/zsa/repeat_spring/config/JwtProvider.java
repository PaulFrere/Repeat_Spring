package ru.zsa.repeat_spring.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
@Log
public class JwtProvider {

    private static final String JWT_SECRET = "kjiugfdkmdrdswq";
    private static final String LOGIN_CLAIM = "login";
    private static final String ROLE_CLAIM = "roles";

    public String generateToken(String login, List<String> roles) {
        Instant expirationTime = Instant.now().plus(5, ChronoUnit.HOURS);
        Date expirationDate = Date.from(expirationTime);

        String compactTokenString = Jwts.builder()
                .claim(LOGIN_CLAIM, login)
                .claim(ROLE_CLAIM, String.join(",", roles))
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
        return "Bearer " + compactTokenString;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.severe("invalid token");
        }
        return false;
    }

    public String getLoginFromToken(String token) {
        return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().get(LOGIN_CLAIM, String.class);
    }

    public List<String> getRolesFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
        return Arrays.asList(claims.get(ROLE_CLAIM, String.class).split(","));
    }
}
