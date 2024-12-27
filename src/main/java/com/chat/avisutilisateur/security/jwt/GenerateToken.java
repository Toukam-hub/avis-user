package com.chat.avisutilisateur.security.jwt;

import com.chat.avisutilisateur.entity.Utilisateur;
import com.chat.avisutilisateur.service.user.GetUserByEmail;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GenerateToken {

    private final GetUserByEmail getUserByEmail;
    private final GetSecretkey getSecretkey;

    public Map<String, String> execute(String email) {
        var utilisateur = (Utilisateur) this.getUserByEmail.loadUserByUsername(email);
        return this.generateJwt(utilisateur);
    }

    private Map<String, String> generateJwt(Utilisateur utilisateur) {

        final long creationTime = System.currentTimeMillis();
        final long expirationTime = creationTime + 30 * 60 * 1000;
        final Map<String, Object> claims = Map.of(
                "nom", utilisateur.getNom(),
                "roles", utilisateur.getRole().getLibelle()
        );
        final String bearer = Jwts.builder()
                .issuedAt(new Date(creationTime))
                .expiration(new Date(expirationTime))
                .subject(utilisateur.getEmail())
                .claims(claims)
                .signWith(getSecretkey.execute())
                .compact();
        return Map.of("bearer", bearer);
    }


}