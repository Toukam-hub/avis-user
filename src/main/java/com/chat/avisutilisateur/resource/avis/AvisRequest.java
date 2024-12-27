package com.chat.avisutilisateur.resource.avis;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AvisRequest(
        @NotBlank(message = "Le message ne peut pas être vide.")
        @Size(min = 5)
        String message,
        String status) {
}
