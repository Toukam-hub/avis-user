package com.chat.avisutilisateur.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "utilisateur")
@Getter
@Setter
public class Utilisateur implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    @Column(name = "mot_de_passe")
    private String mdp;
    @Column(unique = true,nullable = false)
    private String email;
    //on vas envoyer un code par mail pour activer le compte
    private boolean actif = false;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.role.getLibelle()));
    }

    @Override
    public String getPassword() {
        return this.mdp;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.actif;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.actif;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.actif;
    }

    @Override
    public boolean isEnabled() {
        return this.actif;
    }
}