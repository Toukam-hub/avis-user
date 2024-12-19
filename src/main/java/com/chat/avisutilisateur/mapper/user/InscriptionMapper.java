package com.chat.avisutilisateur.mapper.user;

import com.chat.avisutilisateur.entity.Role;
import com.chat.avisutilisateur.entity.Utilisateur;
import com.chat.avisutilisateur.resource.user.CreateUserRequest;

public class InscriptionMapper {

    private InscriptionMapper() {}

    public static Utilisateur map(CreateUserRequest creeUtilisateur, Role role) {
        var utilisateur = new Utilisateur();
        utilisateur.setNom(creeUtilisateur.nom());
        utilisateur.setMdp(creeUtilisateur.mdp());
        utilisateur.setEmail(creeUtilisateur.email());
        utilisateur.setRole(role);
        return utilisateur;
    }
}
