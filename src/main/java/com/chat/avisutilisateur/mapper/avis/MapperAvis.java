package com.chat.avisutilisateur.mapper.avis;

import com.chat.avisutilisateur.entity.Avis;
import com.chat.avisutilisateur.resource.avis.AvisRequest;
import com.chat.avisutilisateur.resource.avis.AvisResponse;

import java.time.Instant;

public class MapperAvis {

    public static final Instant NOW = Instant.now();

    private MapperAvis() {}

    public static Avis map(AvisRequest request) {
        return Avis.builder()
                .message(request.message())
                .status(request.status())
                .dateCreation(NOW)
                .dateModification(NOW)
                .build();
    }

    public static AvisResponse map(Avis avis) {
        return new AvisResponse(
                avis.getId(),
                avis.getMessage(),
                avis.getStatus(),
                avis.getDateCreation(),
                avis.getDateModification()
        );
    }
}
