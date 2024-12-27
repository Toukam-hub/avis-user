package com.chat.avisutilisateur.service.user;

import com.chat.avisutilisateur.entity.Role;
import com.chat.avisutilisateur.entity.Utilisateur;
import com.chat.avisutilisateur.exception.ResourceAlreadyExistException;
import com.chat.avisutilisateur.mapper.user.InscriptionMapper;
import com.chat.avisutilisateur.repository.RoleRepository;
import com.chat.avisutilisateur.repository.UtilisateurRepository;
import com.chat.avisutilisateur.resource.user.CreateUserRequest;
import com.chat.avisutilisateur.service.validation.CreateValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.chat.avisutilisateur.emus.TypeRole.UTILISATEUR;

@Service
@RequiredArgsConstructor
@Slf4j
public class Inscription {

    private final RoleRepository roleRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CreateValidation enregisterUneValidation;

    public String execute(CreateUserRequest request) {
        log.info("user registration with : {}", request);
        if (this.utilisateurRepository.existsByEmail(request.email())) {
            throw new ResourceAlreadyExistException("Cette Email existe deja !");
        }
        Role role = roleRepository.findByLibelle(UTILISATEUR)
                .orElseGet(() -> new Role(UTILISATEUR));
        Utilisateur utilisateur = InscriptionMapper.map(request, role);
        String mdpCrypte = this.passwordEncoder.encode(utilisateur.getMdp());
        utilisateur.setMdp(mdpCrypte);
        utilisateur = this.utilisateurRepository.save(utilisateur);
        log.info("utilisateur enregister : {}", utilisateur);
       this.enregisterUneValidation.execute(utilisateur);
        return "Inscription réussi !";
    }
}