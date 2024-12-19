package com.chat.avisutilisateur.service.validation;

import com.chat.avisutilisateur.entity.Validation;
import com.chat.avisutilisateur.repository.ValidationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetValidationByCode {

    private final ValidationRepository validationRepository;

    public Validation execute(String activation) {
        return this.validationRepository.findByCodeActivation(activation).orElseThrow(
                () -> new IllegalArgumentException("Votre code est invalide")
        );
    }
}