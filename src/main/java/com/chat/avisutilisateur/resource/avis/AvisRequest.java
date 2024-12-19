package com.chat.avisutilisateur.resource.avis;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AvisRequest(
        @NotBlank
        @Size(min = 5)
        String message,
        String status) {
}
