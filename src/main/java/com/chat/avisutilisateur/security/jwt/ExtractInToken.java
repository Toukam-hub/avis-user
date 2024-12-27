package com.chat.avisutilisateur.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ExtractInToken {
    private final GetSecretkey getSecretkey;

    public final String getEmail(String token) {
        return this.getClam(token, Claims::getSubject);
    }

    public boolean isTokenExpired(String token) {
        Date expiretionDate = this.getClam(token, Claims::getExpiration);
        return expiretionDate.before(new Date());
    }


    private <T> T getClam(String token, Function<Claims, T> function) {
        Claims claims = getAllClaims(token);
        return function.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretkey.execute())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
