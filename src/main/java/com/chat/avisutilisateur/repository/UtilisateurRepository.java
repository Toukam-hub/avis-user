package com.chat.avisutilisateur.repository;

import com.chat.avisutilisateur.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    boolean existsByEmail(String email);
    Optional<Utilisateur> findByEmail(String email);
}