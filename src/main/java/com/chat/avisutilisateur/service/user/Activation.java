package com.chat.avisutilisateur.service.user;

import com.chat.avisutilisateur.exception.ResourceExpriredException;
import com.chat.avisutilisateur.exception.ResourceNotFoundException;
import com.chat.avisutilisateur.repository.UtilisateurRepository;
import com.chat.avisutilisateur.service.validation.GetValidationByCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class Activation {

    private final GetValidationByCode validationByCode;
    private final UtilisateurRepository utilisateurRepository;

    public void execute(Map<String, String> request) {
        log.info("request et code {}", request);
        String code = request.get("code");
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Le champ 'code' est obligatoire");
        }
        var validation = this.validationByCode.execute(code);
        if (Instant.now().isAfter(validation.getExpiration())) {
            throw new ResourceExpriredException("Votre code a expiré");
        }
        var urilisateurAactive = this.utilisateurRepository.findById(validation.getUtilisateur().getId()).orElseThrow(
                () -> new ResourceNotFoundException("utilisateur inconnue")
        );
        urilisateurAactive.setActif(true);
        this.utilisateurRepository.save(urilisateurAactive);
    }
}