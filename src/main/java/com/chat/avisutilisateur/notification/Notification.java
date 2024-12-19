package com.chat.avisutilisateur.notification;

import com.chat.avisutilisateur.entity.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Notification {
    private final JavaMailSender javaMailSender;

    public void envoyer(Validation validation) {
        String text = String.format("""
                                            Bonjour %s, <br />
                                            Votre code d'activation est %s,
                                            À bientôt
                                            """, validation.getUtilisateur().getNom(), validation.getCodeActivation());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("neambo@service.tech");
        message.setTo(validation.getUtilisateur().getEmail());
        message.setSubject("Votre code d'activation");
        message.setText(text);
        javaMailSender.send(message);

    }
}