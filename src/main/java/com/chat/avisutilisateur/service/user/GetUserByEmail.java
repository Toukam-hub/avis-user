package com.chat.avisutilisateur.service.user;

import com.chat.avisutilisateur.exception.ResourceNotFoundException;
import com.chat.avisutilisateur.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserByEmail implements UserDetailsService {
    private final UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.utilisateurRepository.findByEmail(username).orElseThrow(
                () -> new ResourceNotFoundException("Aucun utilisateur ne correspond a ces identifiants")
        );
    }
}
