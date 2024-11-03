package com.project.mindly.service.view;

import com.project.mindly.model.view.AgendaProfissionalView;
import com.project.mindly.repository.view.AgendaProfissionalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendaProfissionalService {

    private final AgendaProfissionalRepository agendaProfissionalRepository;


    public AgendaProfissionalService(AgendaProfissionalRepository agendaProfissionalRepository) {
        this.agendaProfissionalRepository = agendaProfissionalRepository;
    }

    public List<AgendaProfissionalView> findAgendaProfissionalAll() {
        return agendaProfissionalRepository.findAll();
    }

    public Optional<AgendaProfissionalView> findAgendaProfissionalByCpf(String cpf) {
        return Optional.ofNullable(agendaProfissionalRepository.findById(cpf)
                .orElseThrow(()-> new EntityNotFoundException("Profissional não encontrado com  o CPF: "+ cpf)));
    }
}
