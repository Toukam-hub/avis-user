package com.chat.avisutilisateur.service.avis;

import com.chat.avisutilisateur.exception.ResourceNotFoundException;
import com.chat.avisutilisateur.mapper.avis.MapperAvis;
import com.chat.avisutilisateur.repository.AvisRepository;
import com.chat.avisutilisateur.resource.avis.AvisResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAvis {

    private final AvisRepository avisRepository;

    public AvisResponse execute(int id) {
        return MapperAvis.map(avisRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cette avis n'existe pas")
        ));
    }
}
