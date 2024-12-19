package com.chat.avisutilisateur.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "validation")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Validation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Instant creation;
    private Instant expiration;
    private String codeActivation;

    @OneToOne(cascade = CascadeType.ALL)
    private Utilisateur utilisateur;

}
