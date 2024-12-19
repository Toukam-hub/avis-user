package com.chat.avisutilisateur.resource.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @NotBlank
        @Size(min = 3)
        String nom,

        @NotBlank
        String mdp,
        @Email
        String email
) {}