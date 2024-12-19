package com.chat.avisutilisateur.repository;

import com.chat.avisutilisateur.emus.TypeRole;
import com.chat.avisutilisateur.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByLibelle(TypeRole libelle);
}