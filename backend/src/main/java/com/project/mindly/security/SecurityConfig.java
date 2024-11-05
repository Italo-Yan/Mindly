package com.project.mindly.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final SecurityFilterPaciente securityFilterPaciente;

    public SecurityConfig(SecurityFilterPaciente securityFilterPaciente) {
        this.securityFilterPaciente = securityFilterPaciente;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         return http
                .csrf(csrf-> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        // EndPoint da aplicação //
                        .requestMatchers(HttpMethod.GET, "/v3/api-docs/swagger-config").permitAll() // Desbloquear swagger
                        .requestMatchers(HttpMethod.GET, "/v3/api-docs").permitAll() // Desbloquear swagger
                        .requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll() // Desbloquear swagger
                        // Profissional //
                        .requestMatchers(HttpMethod.GET,"/profissional/**").hasRole("PROFISSIONAL")
                        .requestMatchers(HttpMethod.POST,"/profissional/**").hasRole("PROFISSIONAL")
                        .requestMatchers(HttpMethod.PATCH,"/profissional/**").hasRole("PROFISSIONAL")
                        .requestMatchers(HttpMethod.DELETE,"/profissional/**").hasRole("PROFISSIONAL")
                        .requestMatchers(HttpMethod.GET,"/agenda/**").hasRole("PROFISSIONAL")
                        .requestMatchers(HttpMethod.POST,"/agenda/**").hasRole("PROFISSIONAL")
                        .requestMatchers(HttpMethod.PATCH,"/agenda/**").hasRole("PROFISSIONAL")
                        .requestMatchers(HttpMethod.DELETE,"/agenda/**").hasRole("PROFISSIONAL")
                        .requestMatchers(HttpMethod.GET,"/agendamento/**").hasRole("PROFISSIONAL")
                        .requestMatchers(HttpMethod.POST,"/agendamento/**").hasRole("PROFISSIONAL")
                        .requestMatchers(HttpMethod.PATCH,"/agendamento/**").hasRole("PROFISSIONAL")
                        .requestMatchers(HttpMethod.DELETE,"/agendamento/**").hasRole("PROFISSIONAL")
                        .requestMatchers(HttpMethod.GET,"/sessao/**").hasRole("PROFISSIONAL")
                        // Paciente //
                        .requestMatchers(HttpMethod.POST,"/paciente/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/paciente/create").permitAll()
                        .requestMatchers(HttpMethod.GET,"/paciente/**").hasRole("PACIENTE")
                        .requestMatchers(HttpMethod.POST,"/paciente/**").hasRole("PACIENTE")
                        .requestMatchers(HttpMethod.PATCH,"/paciente/**").hasRole("PACIENTE")
                        .requestMatchers(HttpMethod.DELETE,"/paciente/**").hasRole("PACIENTE")
                        .requestMatchers(HttpMethod.GET,"/profissionais/publico").hasRole("PACIENTE")
                        .requestMatchers(HttpMethod.GET,"/agenda/**").hasRole("PACIENTE")
                        .requestMatchers(HttpMethod.GET,"/sessao/**").hasRole("PACIENTE")
                        .requestMatchers(HttpMethod.GET,"/agendamento/**").hasRole("PACIENTE")
                        .anyRequest().authenticated())
                 .addFilterBefore(securityFilterPaciente, UsernamePasswordAuthenticationFilter.class)
                 //.addFilterBefore(securityFilterProfissional,UsernamePasswordAuthenticationFilter.class)
                 .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authconf) throws Exception {
        return authconf.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4,new SecureRandom());
    }
}

