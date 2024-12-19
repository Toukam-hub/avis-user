package com.chat.avisutilisateur.service.avis;

import com.chat.avisutilisateur.mapper.avis.MapperCreateAvis;
import com.chat.avisutilisateur.repository.AvisRepository;
import com.chat.avisutilisateur.resource.avis.AvisRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAvis {
    private final AvisRepository avisRepository;

    public void execute(AvisRequest request){
        this.avisRepository.save(MapperCreateAvis.map(request));
    }
}
