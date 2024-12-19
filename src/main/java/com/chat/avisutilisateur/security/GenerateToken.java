package com.chat.avisutilisateur.security;

import com.chat.avisutilisateur.entity.Utilisateur;
import com.chat.avisutilisateur.service.user.GetUserByEmail;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GenerateToken {

    private final GetUserByEmail utilisateurService;
    @Value("${jwt.secret}")
    private String secretKey;

    public Map<String, String> execute(String email) {
        var utilisateur = (Utilisateur) this.utilisateurService.loadUserByUsername(email);
        return this.generateJwt(utilisateur);
    }

    private Map<String, String> generateJwt(Utilisateur utilisateur) {
        final long creationTime = System.currentTimeMillis();
        final long expirationTime = creationTime + 30 * 60 * 1000;
        final Map<String, String> claims = Map.of(
                "nom", utilisateur.getNom(),
                "email", utilisateur.getEmail()
        );
        final String bearer = Jwts.builder()
                .issuedAt(new Date(creationTime))
                .expiration(new Date(expirationTime))
                .claims(claims)
                .signWith(getKey())
                .compact();
        return Map.of("bearer", bearer);
    }

    private Key getKey() {
        final byte[] decoder = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(decoder);
    }
}