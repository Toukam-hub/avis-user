package com.chat.avisutilisateur.controller;

import com.chat.avisutilisateur.resource.avis.AvisRequest;
import com.chat.avisutilisateur.service.avis.CreateAvis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("avis")
@Slf4j
public class AvisController {
    private final CreateAvis createAvis;

    public AvisController(CreateAvis createAvis) {this.createAvis = createAvis;}

    @PostMapping
    public ResponseEntity<String> created(@RequestBody AvisRequest request) {
        log.info("une avis {}", request);
        this.createAvis.execute(request);
        return new ResponseEntity<>("Avis creer avec succes", HttpStatus.CREATED);
    }

}
