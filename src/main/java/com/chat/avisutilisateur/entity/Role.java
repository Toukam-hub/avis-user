package com.chat.avisutilisateur.entity;

import com.chat.avisutilisateur.emus.TypeRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
@Getter
public class Role implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeRole libelle;

    @OneToMany(mappedBy = "role")
    private Set<Utilisateur> utilisateurs =new HashSet<>();

    public Role(TypeRole libelle) {
        this.libelle =libelle;
    }
}