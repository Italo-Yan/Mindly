package com.project.mindly.service;


import com.project.mindly.model.view.ProfissionalPublicoView;
import com.project.mindly.repository.ProfissionalPublicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfissionalPublicoService {

    private final ProfissionalPublicoRepository profissionalPublicoRepository;


    public ProfissionalPublicoService(ProfissionalPublicoRepository profissionalPublicoRepository) {
        this.profissionalPublicoRepository = profissionalPublicoRepository;
    }

    public List<ProfissionalPublicoView> findProfissionalPublicoAll() {
        return profissionalPublicoRepository.findAll();
    }

    public Optional<ProfissionalPublicoView> findrofissionalPublicoByCpf(String cpf) {
        return Optional.ofNullable(profissionalPublicoRepository.findById(cpf)
                .orElseThrow(()-> new EntityNotFoundException("Profissional não encontrado com  o CPF: "+ cpf)));
    }
}
