package com.project.mindly.service;

import com.project.mindly.model.paciente.Paciente;
import com.project.mindly.model.profissional.Profissional;
import com.project.mindly.repository.PacienteRepository;
import com.project.mindly.repository.ProfissionalRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PacienteRepository pacienteRepository;
    private final ProfissionalRepository profissionalRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(PacienteRepository pacienteRepository, ProfissionalRepository profissionalRepository, PasswordEncoder passwordEncoder) {
        this.pacienteRepository = pacienteRepository;
        this.profissionalRepository = profissionalRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public boolean authenticatePaciente(String email, String senha) {
        Paciente paciente = pacienteRepository.findByEmailPaciente(email);
        return paciente != null && passwordEncoder.matches(senha, paciente.getSenha());
    }

    public boolean authenticateProfissional(String email, String senha) {
        Profissional profissional = profissionalRepository.findByEmailProf(email);
        return profissional != null && passwordEncoder.matches(senha, profissional.getSenha());
    }


}
