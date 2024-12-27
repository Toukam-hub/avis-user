package com.chat.avisutilisateur.resource.avis;

import java.time.Instant;

public record AvisResponse(
        int id,
        String message,
        String status,
        Instant dateCreation,
        Instant dateModification
) {}
