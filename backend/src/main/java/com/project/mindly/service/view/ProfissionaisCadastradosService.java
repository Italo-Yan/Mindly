package com.project.mindly.service.view;


import com.project.mindly.model.view.ProfissionaisCadastradosView;
import com.project.mindly.repository.view.ProfissionaisCadastradosRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfissionaisCadastradosService {

    private final ProfissionaisCadastradosRepository profissionaisCadastradosRepository;

    public ProfissionaisCadastradosService(ProfissionaisCadastradosRepository profissionaisCadastradosRepository) {
        this.profissionaisCadastradosRepository = profissionaisCadastradosRepository;
    }

    public List<ProfissionaisCadastradosView> findProfissionaisCadastradosAll() {
        return profissionaisCadastradosRepository.findAll();
    }

    public Optional<ProfissionaisCadastradosView> findProfissionaisCadastradosByCpf(String cpf) {
        return Optional.ofNullable(profissionaisCadastradosRepository.findById(cpf)
                .orElseThrow(()-> new EntityNotFoundException("Profissional não encontrado com  o CPF: "+ cpf)));
    }
}
