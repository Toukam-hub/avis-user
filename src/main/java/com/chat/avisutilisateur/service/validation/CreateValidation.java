package com.chat.avisutilisateur.service.validation;

import com.chat.avisutilisateur.entity.Utilisateur;
import com.chat.avisutilisateur.entity.Validation;
import com.chat.avisutilisateur.notification.Notification;
import com.chat.avisutilisateur.repository.ValidationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;

import static java.time.temporal.ChronoUnit.MINUTES;

@Service
@RequiredArgsConstructor
public class CreateValidation {
    private final ValidationRepository validationRepository;
    private final Notification notification;
    private final Random random = new Random();

    public void execute(Utilisateur utilisateur) {
        System.out.println("hello tous le monde");
        int randomInteger = random.nextInt(999999);
        String code = String.format("%06d", randomInteger);
        Instant now = Instant.now();
        var validation = Validation.builder()
                .creation(now)
                .expiration(now.plus(10, MINUTES))
                .codeActivation(code)
                .utilisateur(utilisateur)
                .build();
        this.validationRepository.save(validation);
        this.notification.envoyer(validation);
    }
}