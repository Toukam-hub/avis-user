package com.chat.avisutilisateur.mapper.avis;

import com.chat.avisutilisateur.entity.Avis;
import com.chat.avisutilisateur.resource.avis.AvisRequest;

public class MapperCreateAvis {

    private MapperCreateAvis(){}

    public static Avis map(AvisRequest request){
        return Avis.builder()
                .message(request.message())
                .status(request.status())
                .build();
    }
}
