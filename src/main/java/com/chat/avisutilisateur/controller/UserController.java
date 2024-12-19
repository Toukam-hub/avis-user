package com.chat.avisutilisateur.controller;

import com.chat.avisutilisateur.resource.user.ConnexionRequest;
import com.chat.avisutilisateur.resource.user.CreateUserRequest;
import com.chat.avisutilisateur.security.GenerateToken;
import com.chat.avisutilisateur.service.user.Activation;
import com.chat.avisutilisateur.service.user.Inscription;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("users")
@Slf4j
public class UserController {
    private final Inscription inscription;
    private final Activation activation;
    private final AuthenticationManager authenticationManager;
    private final GenerateToken generateToken;

    public UserController(Inscription inscription, Activation activation, AuthenticationManager authenticationManager, GenerateToken generateToken) {
        this.inscription = inscription;
        this.activation = activation;
        this.authenticationManager = authenticationManager;
        this.generateToken = generateToken;
    }

    @PostMapping("/inscription")
    public ResponseEntity<String> inscription(@RequestBody CreateUserRequest createUserRequest) {
        return new ResponseEntity<>(this.inscription.execute(createUserRequest), HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/activation")
    public void activation(@RequestBody Map<String, String> request) {
        activation.execute(request);
    }

    @PostMapping("/connexion")
    public Map<String, String> connexion(@RequestBody ConnexionRequest request) {
        log.info("connexion request: {}", request);
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.mdp())
        );
        return this.generateToken.execute(request.email());
    }
}
