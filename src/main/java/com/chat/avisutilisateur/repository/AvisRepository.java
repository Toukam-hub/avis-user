package com.chat.avisutilisateur.repository;

import com.chat.avisutilisateur.entity.Avis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisRepository extends JpaRepository<Avis,Integer> {
}
