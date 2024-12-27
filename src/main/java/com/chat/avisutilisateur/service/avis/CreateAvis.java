package com.chat.avisutilisateur.service.avis;

import com.chat.avisutilisateur.mapper.avis.MapperAvis;
import com.chat.avisutilisateur.repository.AvisRepository;
import com.chat.avisutilisateur.resource.avis.AvisRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateAvis {

    private final AvisRepository avisRepository;

    public void execute(AvisRequest request) {
        log.info("créer un avis avec : {}", request);
        this.avisRepository.save(MapperAvis.map(request));
    }
}
