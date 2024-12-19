package com.chat.avisutilisateur.repository;

import com.chat.avisutilisateur.entity.Validation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ValidationRepository extends JpaRepository<Validation, Integer> {
    Optional<Validation> findByCodeActivation(String code);
}