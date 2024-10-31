package com.project.mindly.service;

import com.project.mindly.model.view.PerfilPacienteProfissionalView;
import com.project.mindly.repository.PerfilPacienteProfissionalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilPacienteProfissionalService {

    private final PerfilPacienteProfissionalRepository  perfilPacienteProfissionalRepository;


    public PerfilPacienteProfissionalService(PerfilPacienteProfissionalRepository perfilPacienteProfissionalRepository) {
        this.perfilPacienteProfissionalRepository = perfilPacienteProfissionalRepository;
    }

    public List<PerfilPacienteProfissionalView> findPerfilPacienteProfissionalAll() {
        return perfilPacienteProfissionalRepository.findAll();
    }

    public Optional<PerfilPacienteProfissionalView> findPerfilPacienteProfissionalById(String cpf) {
        return Optional.ofNullable(perfilPacienteProfissionalRepository.findById(cpf)
                .orElseThrow(()-> new EntityNotFoundException("Paciente não encontrado com  o CPF: "+ cpf)));
    }
}
