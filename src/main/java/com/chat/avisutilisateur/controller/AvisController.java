package com.chat.avisutilisateur.controller;

import com.chat.avisutilisateur.resource.avis.AvisRequest;
import com.chat.avisutilisateur.resource.avis.AvisResponse;
import com.chat.avisutilisateur.service.avis.CreateAvis;
import com.chat.avisutilisateur.service.avis.GetAvis;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("avis")
public class AvisController {
    private final CreateAvis createAvis;
    private final GetAvis getAvis;

    public AvisController(CreateAvis createAvis, GetAvis getAvis) {
        this.createAvis = createAvis;
        this.getAvis = getAvis;
    }

    @PostMapping
    public ResponseEntity<Void> createAvis(@RequestBody AvisRequest request){
        this.createAvis.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvisResponse> getAvis(@PathVariable int id) {
        return ResponseEntity.ok(this.getAvis.execute(id));
    }
}
