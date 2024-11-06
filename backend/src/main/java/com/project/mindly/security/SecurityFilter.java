package com.project.mindly.security;

import com.project.mindly.repository.PacienteRepository;
import com.project.mindly.repository.ProfissionalRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final PacienteRepository pacienteRepository;
    private final ProfissionalRepository profissionalRepository;

    public SecurityFilter(TokenService tokenService, PacienteRepository pacienteRepository, ProfissionalRepository profissionalRepository) {
        this.tokenService = tokenService;
        this.pacienteRepository = pacienteRepository;
        this.profissionalRepository = profissionalRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Evita autenticar novamente se já existe um usuário autenticado no contexto
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            var token = recoverToken(request);

            if (token != null) {
                String username = tokenService.getUsernameFromToken(token); // Extrai o nome de usuário

                String role = tokenService.getRoleFromToken(token); // Extrai a função do token (PACIENTE ou PROFISSIONAL)

                UserDetails user = null;

                if ("auth-api-paciente".equals(role)) {
                    user = pacienteRepository.findByEmailPaciente(username);
                } else if ("auth-api-profissional".equals(role)) {
                    user = profissionalRepository.findByEmailProf(username);
                }

                if (user != null) {
                    var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            return authHeader.replace("Bearer ", "");
        }

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("AUTHTOKEN".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }
}
