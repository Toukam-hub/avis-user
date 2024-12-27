package com.chat.avisutilisateur.security;

import com.chat.avisutilisateur.security.jwt.ExtractInToken;
import com.chat.avisutilisateur.service.user.GetUserByEmail;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    public static final String BASE_URL = "/users";
    private final GetUserByEmail getUserByEmail;
    private final ExtractInToken extraction;
    private static final List<String> EXCLUDED_URLS = List.of(
            BASE_URL + "/inscription",
            BASE_URL + "/activation",
            BASE_URL + "/connexion"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        if (EXCLUDED_URLS.stream().anyMatch(path::contains)) {
            filterChain.doFilter(request, response);
            return;
        }
        if (request.getHeader("Authorization") != null) {
            final String authorization = request.getHeader("Authorization");

            String token = authorization.substring(7);
            String email = extraction.getEmail(token);
            boolean isTokenExpired = extraction.isTokenExpired(token);
            if (!isTokenExpired && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = getUserByEmail.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            filterChain.doFilter(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "le token ne doit pas êtres null.");
            return;
        }
        filterChain.doFilter(request, response);
    }
}
