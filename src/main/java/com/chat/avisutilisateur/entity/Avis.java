package com.chat.avisutilisateur.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "avis")
public class Avis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String message;
    private String status;
    @Column(name = "date_de_creation")
    private Instant dateCreation;
    @Column(name = "date_de_modification")
    private Instant dateModification;
}
