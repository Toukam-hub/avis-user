package com.chat.avisutilisateur.resource.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ConnexionRequest(
        @Email
        String email,
        @NotBlank
        String mdp) {
}
